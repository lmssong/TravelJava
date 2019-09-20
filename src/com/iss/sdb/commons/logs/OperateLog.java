/**
 * OperateLog.java 2016年9月6日
 * 
 * Copyright 2001-2016  All rights reserved.
 *  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.commons.logs;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作日志注解器
 * 
 * @author hqsunc
 * @since 2016年9月6日
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLog {

    /**
     * 操作动作
     * 如：新增、修改、删除等
     */
    OperateVerb verb();

    /**
     * 操作对象
     * 如：教材、用户、公开课
     */
    String noun();
}
