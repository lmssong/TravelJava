/**
 * EncryptUtil.java 2016年10月24日
 * 
 * Copyright 2001-2016  All rights reserved.
 *  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.commons.utils;

import java.security.MessageDigest;

import org.apache.commons.lang3.StringUtils;

import com.iss.sdb.pet.commons.Constants.Commons;

/**
 * 加密工具
 * 
 * @author hqsunc
 * @since 2016年10月24日
 *
 */
public final class EncryptUtil {

    /**
     * 默认MD5算法
     */
    private static final String DEFAULT_ALGORITHM = "MD5";

    /**
     * 采用加密算法加密字符串数据
     *
     * @author hqsunc
     * @param str
     *            待加密字符串
     * @param algorithm
     *            加密算法
     * @param charset
     *            编码格式
     * @return
     * @since 2016年10月24日
     * @see
     */
    public static String encrypt(String str, String algorithm, String charset) {

        MessageDigest md = null;

        try {
            // 获取算法实例，默认MD5
            if (StringUtils.isBlank(algorithm)) {
                md = MessageDigest.getInstance(DEFAULT_ALGORITHM);
            }
            else {
                md = MessageDigest.getInstance(algorithm);
            }

            // 添加要进行计算摘要的信息
            if (StringUtils.isBlank(charset)) {
                md.update(str.getBytes(Commons.CHARACTER_SET));
            }
            else {
                md.update(str.getBytes(charset));
            }

            byte[] bytes = md.digest();
            StringBuilder digestBuilder = new StringBuilder();
            String s = "";
            for (byte bt : bytes) {
                s = Integer.toHexString(0xff & bt);
                if (s.length() == 1) {
                    digestBuilder.append("0").append(s);
                }
                else {
                    digestBuilder.append(s);
                }
            }

            return digestBuilder.toString();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 采用加密算法加密字符串数据
     *
     * @author hqsunc
     * @param str
     *            待加密字符串
     * @param algorithm
     *            加密算法
     * @return
     * @since 2016年10月24日
     * @see
     */
    public static String encrypt(String str, String algorithm) {
        return encrypt(str, algorithm, Commons.CHARACTER_SET);
    }

    /**
     * 采用MD5算法加密字符串数据
     *
     * @author hqsunc
     * @param str
     *            待加密字符串
     * @return
     * @since 2016年10月24日
     * @see
     */
    public static String md5(String str) {
        return encrypt(str, DEFAULT_ALGORITHM);
    }

    /**
     * 私有构造
     */
    private EncryptUtil() {
    }
}
