/**
 * DBLogMapper.java 2016年9月25日
 * 
 * Copyright 2001-2016  All rights reserved.
 *  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.commons.logs.dao;

import com.iss.sdb.commons.models.LogModel;

/**
 * DB Log Mapper
 * 
 * @author hqsunc
 * @since 2016年9月25日
 *
 */
public interface LogMapper {

    /**
     * 添加日志
     *
     * @author hqsunc
     * @param log
     *            操作日志数据模型
     * @since 2016年9月25日
     */
    void add(LogModel log);
}
