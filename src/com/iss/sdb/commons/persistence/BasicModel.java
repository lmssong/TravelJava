/**
 * BasicModel.java 2016年9月20日
 * 
 * Copyright 2001-2016  All rights reserved.
 *  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.commons.persistence;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 持久化存储基模型
 * 
 * @author hqsunc
 * @since 2016年9月20日
 *
 */
public abstract class BasicModel {

    /**
     * 状态常量：常规
     */
    public final static Integer STATUS_NORMAL = 1;

    /**
     * 状态常量：已删除
     */
    public final static Integer STATUS_DELETE = 2;
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
