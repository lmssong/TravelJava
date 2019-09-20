/**
 * FileUploadSupport.java 2016年9月22日
 * 
 * Copyright 2001-2016  All rights reserved.
 *  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.pet.commons;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iss.sdb.commons.models.FileUpResModel;
import com.iss.sdb.commons.utils.MultipartResolver;

/**
 * 公共文件上传
 * 
 * @author hqsunc
 * @since 2016年9月22日
 *
 */
@Component
public class FileUploadSupport {

    /**
     * 统一上传文件路径前缀
     */
    private static final String RESOURCES = "resources/";
    

    /**
     * 多文件上传分解器
     */
    @Autowired
    private MultipartResolver multipartResolver;

    /**
     * 上传教辅资源文件
     *
     * @author hqsunc
     * @param request
     * @return
     * @throws IOException
     * @since 2016年9月22日
     */
    public List<FileUpResModel> lawResourceFile(HttpServletRequest request) throws IOException {
        List<FileUpResModel> fileList = multipartResolver.upload(request, RESOURCES);
        for(FileUpResModel file : fileList){
            file.setConvertFileName(file.getNewFileName());
        }
        return fileList;
    }
}
