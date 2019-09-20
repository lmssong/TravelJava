/**
 * OperateVerb.java 2016年9月6日
 * 
 * Copyright 2001-2016  All rights reserved.
 *  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.commons.logs;

/**
 * 操作动词枚举
 * 
 * @author hqsunc
 * @since 2016年9月6日
 *
 */
public enum OperateVerb {

    ADD("新增", 1), MODIFY("修改", 2), DEL("删除", 3), GET("查看", 4), PUBLISH("发布", 5), IMPORT("导入", 6), EXPORT("导出", 7);

    private String value = "";

    private int idx;

    private OperateVerb(String value, int idx) {
        this.value = value;
        this.idx = idx;
    }

    public String getValue() {
        return this.value;
    }

    public int getIdx() {
        return this.idx;
    }

}
