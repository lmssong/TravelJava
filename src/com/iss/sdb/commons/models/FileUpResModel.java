/**
 * FileUploadModel.java 2016年9月22日
 * 
 * Copyright 2001-2016  All rights reserved.
 *  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.commons.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 文件上传返回信息
 * 
 * @author hqsunc
 * @since 2016年9月22日
 *
 */
public class FileUpResModel {

    /**
     * 原文件名
     */
    private String originalFileName;

    /**
     * 新生成的文件名
     */
    private String newFileName;
    
    /**
     * 转换后的文件名
     */
    private String convertFileName;

    /**
     * 文件后缀名
     */
    private String extName;

    /**
     * 默认构造
     */
    public FileUpResModel() {
    }

    /**
     * 初始化构造
     * 
     * @param originalFileName
     *            原文件名
     * @param newFileName
     *            新生成的文件名
     * @param 文件后缀名
     */
    public FileUpResModel(String originalFileName, String newFileName, String extName) {
        this.originalFileName = originalFileName;
        this.newFileName = newFileName;
        this.extName = extName;
    }

    /**
     * @return the originalFileName
     * @see FileUpResModel#originalFileName
     */
    public String getOriginalFileName() {
        return originalFileName;
    }

    /**
     * @param originalFileName
     *            the originalFileName to set
     * @see FileUpResModel#originalFileName
     */
    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    /**
     * @return the newFileName
     * @see FileUpResModel#newFileName
     */
    public String getNewFileName() {
        return newFileName;
    }

    /**
     * @param newFileName
     *            the newFileName to set
     * @see FileUpResModel#newFileName
     */
    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    /**
     * @return the convertFileName
     * @see FileUpResModel#convertFileName
     */
    public String getConvertFileName() {
        return convertFileName;
    }

    /**
     * @param convertFileName the convertFileName to set
     * @see FileUpResModel#convertFileName
     */
    public void setConvertFileName(String convertFileName) {
        this.convertFileName = convertFileName;
    }

    /**
     * @return the extName
     * @see FileUpResModel#extName
     */
    public String getExtName() {
        return extName;
    }

    /**
     * @param extName
     *            the extName to set
     * @see FileUpResModel#extName
     */
    public void setExtName(String extName) {
        this.extName = extName;
    }

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
