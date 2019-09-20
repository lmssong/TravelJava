/**
 * MultipartResolver.java 2016年9月22日
 * 
 * Copyright 2001-2016  All rights reserved.
 *  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.commons.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.iss.sdb.commons.models.FileUpResModel;

/**
 * 多文件上传分解器
 * 
 * @author hqsunc
 * @since 2016年9月22日
 *
 */
public class MultipartResolver extends CommonsMultipartResolver {

    /**
     * 默认构造初始化
     */
    public MultipartResolver(PropertiesHolder propertiesHolder) {
        super();

        super.setDefaultEncoding("UTF-8");

        // 配置上传的文件最大限制 MB
        long max = -1;
        max = propertiesHolder.bigInteger("sys.upload.file.max", max);
        max = max * 1024 * 1024;

        // 写入磁盘缓存大小
        super.setMaxInMemorySize(40960);

        // 上传总文件大小
        super.setMaxUploadSize(max);

        // 上传每个文件大小
        super.setMaxUploadSizePerFile(max);
    }

    /**
     * 上传文件
     *
     * @author hqsunc
     * @param request
     *            请求
     * @param path
     *            文件存储目的路径
     * @return 文件存储信息
     * @throws IOException
     * @since 2016年9月22日
     */
    public List<FileUpResModel> upload(HttpServletRequest request, String path) throws IOException {

        this.setServletContext(request.getServletContext());
        
        String appPath = "/upload/";

        String originalFileName = "";
        String newFileName = "";
        String extName = "";

        List<FileUpResModel> fileUpResLst = null;

        if (this.isMultipart(request)) {
            fileUpResLst = new ArrayList<FileUpResModel>();
            FileUpResModel fileUpRes = null;

            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            // 取得request中的所有文件名
            Iterator<String> itor = multiRequest.getFileNames();
            MultipartFile file = null;

            while (itor.hasNext()) {
                // 取得上传文件
                file = multiRequest.getFile(itor.next());

                if (file != null) {
                    // 取得当前上传文件的文件名称
                    originalFileName = file.getOriginalFilename();

                    if (StringUtils.isNotBlank(originalFileName)) {
                        File localFile = new File(appPath + path);
                        if (!localFile.exists()) {
                            localFile.mkdirs();
                        }

                        // 重命名上传后的文件名
                        newFileName = appPath + path + UUID.randomUUID().toString();

                        if (StringUtils.contains(originalFileName, ".")) {
                            extName = StringUtils.substringAfterLast(originalFileName, ".");
                            newFileName += ".";
                            newFileName += extName;
                        }

                        localFile = new File(newFileName);
                        file.transferTo(localFile);

                        newFileName = StringUtils.substringAfterLast(newFileName, appPath);
                        fileUpRes = new FileUpResModel(originalFileName, newFileName, extName);
                        fileUpResLst.add(fileUpRes);
                    }
                }
            }
        }

        return fileUpResLst;
    }

    /**
     * 获取本地服务路径
     *
     * @author hqsunc
     * @param request
     *            请求
     * @return
     * @since 2016年9月22日
     */
    public static String getAppPath(HttpServletRequest request) {

        String contextPath = request.getContextPath();
        if (StringUtils.startsWith(contextPath, "/")) {
            contextPath = StringUtils.substringAfter(contextPath, "/");
        }

        String path = request.getServletContext().getRealPath("/");
        return StringUtils.substringBeforeLast(path, contextPath);
    }
}
