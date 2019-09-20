/**
 * PreparePaginationInterceptor.java 2016年9月21日
 * 
 * Copyright 2001-2016  All rights reserved.
 *  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.commons.paginations;

import java.sql.Connection;
import java.util.Properties;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.iss.sdb.pet.pojo.Page;

/**
 * Mybatis数据库分页插件，拦截StatementHandler的prepare方法
 * 
 * @author hqsunc
 * @since 2016年9月22日
 *
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {
                Connection.class, Integer.class
        })
})
public class PreparePaginationInterceptor extends BaseInterceptor {

    private static final long serialVersionUID = 1L;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.
     * Invocation)
     */
    @Override
    public Object intercept(Invocation ivk) throws Throwable {
        if (ivk.getTarget().getClass().isAssignableFrom(RoutingStatementHandler.class)
                && ArrayUtils.isNotEmpty(ivk.getArgs())) {

            final RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
            final BaseStatementHandler delegate = (BaseStatementHandler) Reflections.getFieldValue(statementHandler,
                    DELEGATE);
            final MappedStatement mappedStatement = (MappedStatement) Reflections.getFieldValue(delegate,
                    MAPPED_STATEMENT);

            BoundSql boundSql = delegate.getBoundSql();
            Object parameterObject = boundSql.getParameterObject();

            String originalSql = boundSql.getSql();

            if (null != parameterObject && StringUtils.isNotBlank(originalSql)) {
                Page page = convertParameter(parameterObject);

                if (null != page) {
                    final Connection connection = (Connection) ivk.getArgs()[0];

                    // 记录统计
                    final int total = SQLHelper.getCount(originalSql, connection, mappedStatement, parameterObject,
                            boundSql, log);
                    page.setTotal(Integer.valueOf(total));

                    String pagingSql = SQLHelper.generatePageSql(originalSql, page, dialect);
                    if (log.isDebugEnabled()) {
                        log.debug("PAGE SQL:" + pagingSql);
                    }
                    // 将分页sql语句反射回BoundSql.
                    Reflections.setFieldValue(boundSql, "sql", pagingSql);
                }

            }

        }
        return ivk.proceed();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object)
     */
    @Override
    public Object plugin(Object o) {
        // 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数
        if (o instanceof StatementHandler) {
            return Plugin.wrap(o, this);
        }

        return o;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties)
     */
    @Override
    public void setProperties(Properties properties) {
    }
}
