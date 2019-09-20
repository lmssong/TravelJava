/**
 * BaseInterceptor.java 2016年9月21日
 * 
 * Copyright 2001-2016  All rights reserved.
 *  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.commons.paginations;

import java.io.Serializable;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;

import com.iss.sdb.pet.pojo.Page;

/**
 * Mybatis分页拦截器基类
 * 
 * @author hqsunc
 * @since 2016年9月22日
 *
 */
public abstract class BaseInterceptor implements Interceptor, Serializable {

    private static final long serialVersionUID = 1L;

    protected static final String PAGE = "page";

    protected static final String DELEGATE = "delegate";

    protected static final String MAPPED_STATEMENT = "mappedStatement";

    protected Log log = LogFactory.getLog(this.getClass());

    protected MySQLDialect dialect = new MySQLDialect();;

    /**
     * 对参数进行转换和检查
     *
     * @author hqsunc
     * @param parameterObject
     *            参数对象
     * @return 分页对象
     * @since 2016年9月22日
     */
    protected static Page convertParameter(Object parameterObject) {
        try {
            if (parameterObject instanceof Page) {
                return (Page) parameterObject;
            }

            return (Page) Reflections.getFieldValue(parameterObject, PAGE);
        }
        catch (Exception e) {
            return null;
        }
    }

}
