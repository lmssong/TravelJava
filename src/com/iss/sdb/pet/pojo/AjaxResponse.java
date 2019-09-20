/**
 * AjaxResponse.java 2016年9月13日
 * 
 * Copyright 2001-2016  All rights reserved.
 *  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.pet.pojo;

import com.alibaba.fastjson.JSON;

/**
 * Ajax 请求响应模型
 * 
 * @author hqsunc
 * @since 2016年9月13日
 *
 */
public class AjaxResponse {
    /**
     * 响应代码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private Object data;

    /**
     * 默认构造函数
     */
    public AjaxResponse() {
    }

    /**
     * 构造函数
     * 
     * @param code
     *            响应消息返回码
     */
    public AjaxResponse(Integer code) {
        this.code = code;
    }

    /**
     * 构造函数
     * 
     * @param isSuccess
     *            以返回状态为初始化条件
     */
    public AjaxResponse(boolean isSuccess) {
        this();

        if (isSuccess) {
            this.toSuccess();
        }
        else {
            this.toError();
        }
    }

    public AjaxResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public AjaxResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 常量代码：成功
     */
    public final static Integer CODE_SUCCESS = 1;

    /**
     * 常量代码：失败
     */
    public final static Integer CODE_ERROR = 2;

    /**
     * 常量消息：成功
     */
    public final static String MESSAGE_SUCCESS = "Operation Successed.";

    /**
     * 常量消息：失败
     */
    public final static String MESSAGE_ERROR = "Operation Error.";

    /**
     * 操作成功实例
     *
     * @author zoe(hqsunc@.com)
     * @return 新的消息响应实例
     * @since 2016年9月22日
     */
    public static AjaxResponse success() {
        AjaxResponse ajax = new AjaxResponse();
        ajax.code = AjaxResponse.CODE_SUCCESS;
        ajax.message = AjaxResponse.MESSAGE_SUCCESS;
        return ajax;
    }

    /**
     * 操作成功实例
     *
     * @author zoe(hqsunc@.com)
     * @param data
     *            返回数据
     * @return 新的消息响应实例
     * @since 2016年9月27日
     */
    public static AjaxResponse success(Object data) {
        AjaxResponse ajax = new AjaxResponse();
        ajax.code = AjaxResponse.CODE_SUCCESS;
        ajax.message = AjaxResponse.MESSAGE_SUCCESS;
        ajax.data = data;
        return ajax;
    }

    /**
     * 重置状态为：成功
     *
     * @author zoe(hqsunc@.com)
     * @return 重置后的响应对象
     * @since 2016年9月23日
     */
    public AjaxResponse toSuccess() {
        this.code = AjaxResponse.CODE_SUCCESS;
        this.message = AjaxResponse.MESSAGE_SUCCESS;
        return this;
    }

    /**
     * 操作失败实例
     *
     * @author zoe(hqsunc@.com)
     * @return 新的消息响应实例
     * @since 2016年9月22日
     */
    public static AjaxResponse error() {
        AjaxResponse ajax = new AjaxResponse();
        ajax.code = AjaxResponse.CODE_ERROR;
        ajax.message = AjaxResponse.MESSAGE_ERROR;
        return ajax;
    }

    /**
     * 操作失败实例
     *
     * @author zoe(hqsunc@.com)
     * @param data
     *            返回数据
     * @return 新的消息响应实例
     * @since 2016年9月27日
     */
    public static AjaxResponse error(Object data) {
        AjaxResponse ajax = new AjaxResponse();
        ajax.code = AjaxResponse.CODE_ERROR;
        ajax.message = AjaxResponse.MESSAGE_ERROR;
        ajax.data = data;
        return ajax;
    }

    /**
     * 重置状态为：失败
     *
     * @author zoe(hqsunc@.com)
     * @return 重置后的响应对象
     * @since 2016年9月23日
     */
    public AjaxResponse toError() {
        this.code = AjaxResponse.CODE_ERROR;
        this.message = AjaxResponse.MESSAGE_ERROR;
        return this;
    }

    /**
     * 从自定义返回码生成消息响应
     *
     * @author zoe(hqsunc@.com)
     * @param code
     *            自定义返回码
     * @return 新的消息响应实例
     * @since 2016年9月22日
     */
    public static AjaxResponse fromCode(Integer code) {
        AjaxResponse ajax = new AjaxResponse();
        ajax.code = code;
        return ajax;
    }

    /**
     * 从自定义返回对象生成响应
     *
     * @author hqsunc(hqsunc@.com)
     * @param data
     *            自定义返回对象
     * @return 新的消息响应对象
     * @since 2016年9月23日
     */
    public static AjaxResponse fromData(Object data) {
        AjaxResponse ajax = new AjaxResponse();
        ajax.data = data;
        return ajax;
    }

    /**
     * 生成 JSON 字元串消息
     *
     * @author zoe(hqsunc@.com)
     * @return 响应消息的 JSON 字元串
     * @since 2016年9月27日
     */
    public String toJSONString() {
        if (this.data instanceof Page) {
            return JSON.toJSONString(this.data);
        }
        else {
            return JSON.toJSONString(this);
        }
    }

    /**
     * @return the code
     * @see AjaxResponse#code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     * @see AjaxResponse#code
     */
    public AjaxResponse setCode(Integer code) {
        this.code = code;
        return this;
    }

    /**
     * @return the message
     * @see AjaxResponse#message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     * @see AjaxResponse#message
     */
    public AjaxResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * @return the data
     * @see AjaxResponse#data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     * @see AjaxResponse#data
     */
    public AjaxResponse setData(Object data) {
        this.data = data;
        return this;
    }
}
