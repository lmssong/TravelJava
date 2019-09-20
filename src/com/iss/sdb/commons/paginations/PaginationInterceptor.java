/**
 * PaginationInterceptor.java 2016年9月21日
 * 
 * Copyright 2001-2016  All rights reserved.
 *  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.commons.paginations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.iss.sdb.pet.pojo.Page;

/**
 * 物理分页控制
 * 
 * @author hqsunc
 * @since 2016年9月21日
 *
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {
                MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class
        })
})
public class PaginationInterceptor implements Interceptor {

    /**
     * logger
     */
    private Log logger = LogFactory.getLog(PaginationInterceptor.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.
     * Invocation)
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        logger.trace("PaginationInterceptor intercept invocation = " + invocation);

        
        
        if (ArrayUtils.isNotEmpty(invocation.getArgs()) && invocation.getArgs().length > 1) {

            Object parameter = invocation.getArgs()[1];

            // 检索物理分析对象
            Page page = seekPagination(parameter);
            logger.trace("PaginationInterceptor intercept pagination = " + page);

            if (null != page && null != page.getCurrentPage() && null != page.getPageSize()
                    && page.getCurrentPage() > 0 && page.getPageSize() > 0) {

                MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
                BoundSql boundSql = mappedStatement.getBoundSql(page.getSearchParam());
                String originalSql = boundSql.getSql().trim();
                Object parameterObject = boundSql.getParameterObject();

                // 组装查询总数sql
                String newSql = getCountSql(originalSql);

                Connection connection = null;
                PreparedStatement countStmt = null;
                ResultSet rs = null;

                try {
                    connection = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
                    countStmt = connection.prepareStatement(newSql);
                    BoundSql countBS = copyFromBoundSql(mappedStatement, boundSql, newSql);
                    DefaultParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement,
                            parameterObject, countBS);
                    parameterHandler.setParameters(countStmt);
                    rs = countStmt.executeQuery();
                    if (rs.next()) {
                        page.setTotal(rs.getInt(1));
                    }

                    logger.trace("PaginationInterceptor intercept countSql = " + newSql);
                }
                finally {
                    if (null != rs) {
                        rs.close();
                    }
                    if (null != countStmt) {
                        countStmt.close();
                    }
                    if (null != connection) {
                        connection.close();
                    }
                }

                // 组装分页执行语句
                int offset = (page.getCurrentPage() - 1) * page.getPageSize();
                offset = offset < 0 ? 0 : offset;

                StringBuffer sb = new StringBuffer();
                sb.append(originalSql).append(" limit ").append(offset).append(",").append(page.getPageSize());

                newSql = sb.toString();

                BoundSql newBoundSql = copyFromBoundSql(mappedStatement, boundSql, newSql);
                MappedStatement newMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));
                invocation.getArgs()[0] = newMs;
                invocation.getArgs()[1] = page.getSearchParam();

                logger.trace("PaginationInterceptor intercept newBoundSql = " + newSql);
                
                /********查询数据放入page对象中****/
                Object result = invocation.proceed();
                page.setRows((Collection<?>) result);
                return result;
            }
        }
        
        return invocation.proceed();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object)
     */
    @Override
    public Object plugin(Object target) {
        logger.trace("PaginationInterceptor plugin object = " + target);

        return Plugin.wrap(target, this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties)
     */
    @Override
    public void setProperties(Properties properties) {
        logger.trace("PaginationInterceptor setProperties properties = " + properties);
    }

    /**
     * 寻找pagination对象
     *
     * @author hqsunc
     * @param parameter
     * @return
     * @since 2016年9月19日
     */
    @SuppressWarnings({
            "rawtypes", "unchecked"
    })
    private Page seekPagination(Object parameter) {
        Page page = null;

        if (null != parameter) {
            if (parameter instanceof Page) {
                page = (Page) parameter;
            }
            else if (parameter instanceof Map) {
                Map<Object, Object> map = (Map) parameter;
                Iterator<Entry<Object, Object>> itor = map.entrySet().iterator();
                Entry<Object, Object> arg = null;

                while (itor.hasNext()) {
                    arg = itor.next();

                    if (arg.getValue() instanceof Page) {
                        page = (Page) arg.getValue();
                        break;
                    }
                }
            }
        }

        return page;
    }

    /**
     * 根据原Sql语句获取对应的查询总记录数的Sql语句
     *
     * @author hqsunc
     * @param sql
     * @return
     * @since 2016年9月19日
     */
    private String getCountSql(String sql) {
        return "SELECT COUNT(*) AS COUNT FROM (" + sql + ") $oepForPage$";
    }

    /**
     * 复制BoundSql对象
     *
     * @author hqsunc
     * @param ms
     * @param boundSql
     * @param sql
     * @return
     * @since 2016年9月20日
     */
    private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql, String sql) {
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(),
                boundSql.getParameterObject());

        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }
        return newBoundSql;
    }

    /**
     * 复制MappedStatement对象
     *
     * @author hqsunc
     * @param ms
     * @param newSqlSource
     * @return
     * @since 2016年9月20日
     */
    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        Builder builder = new Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());

        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        String[] strArr = ms.getKeyProperties();
        if (ArrayUtils.isNotEmpty(strArr)) {
            String properties = "";
            int len = strArr.length;
            for (int i = 0; i < len; i++) {
                properties += strArr[i];
                if (i < len - 1) {
                    properties += ",";
                }
            }
            builder.keyProperty(properties);
        }

        return builder.build();
    }

    /**
     * 实现BoundSqlSqlSource对象
     * 
     * @author hqsunc
     * @since 2016年9月21日
     *
     */
    class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

}
