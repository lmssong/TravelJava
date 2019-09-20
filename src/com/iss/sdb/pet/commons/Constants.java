/**
 * Constants.java 2016年9月28日
 * 
 * Copyright 2001-2016  All rights reserved.
 *  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.pet.commons;

/**
 * 业务常量表
 * 
 * @author zoe(hqsunc@.com)
 * @since 2016年9月28日
 *
 */
public final class Constants {
    public final class Commons {
        /**
         * UTF-8 中文字符
         */
        public static final String CHARACTER_SET = "UTF-8";

        /**
         * 用户会话数据存储键名
         */
        public final static String SESSION_STORE_USER = "sessionUser";

        /**
         * 前台用户SESSION key键
         */
        public final static String SESSION_WEB_USER = "webUser";
        

        /**
         * 电子书导出缓冲区大小
         */
        public final static int EPUB_UNCOMPRESS_BUFFER_SIZE = 1024;

        /**
         * 刀勒符号
         */
        public static final String DOLLAR = "$";

        /**
         * 注册密钥
         */
        public static final String EMAIL_KEY = "OEPREGEMAIL";

        /**
         * 登录验证码
         */
        public static final String VERIFY_CODE = "verifyCode";
    }
    
    public final class PageConfig {
        /**
         * 每页显示用户数
         */
        public final static int PAGE_SIZE = 99999;
    }
    
    /**
     * 系统配置项目
     * 
     * @author hqsunc
     * @since 2017年1月4日
     *
     */
    public static final class SystemConfig {

        /**
         * 系统资源文件存储路径
         */
        public static final String SYSTEM_FILES_PATH = "system.files.path";

        /**
         * 用户资源文件存储路径
         */
        public static final String USER_FILES_PATH = "user.files.path";

        /**
         * 域名
         */
        public static final String DOMAIN_NAME = "domain.name";

        /**
         * 邮箱服务器
         */
        public static final String MAIL_SERVER = "mail.server";

        /**
         * 用户名
         */
        public static final String MAIL_USER = "mail.user";

        /**
         * 密码
         */
        public static final String MAIL_PASSWORD = "mail.password";

        /**
         * 发件人邮箱
         */
        public static final String MAIL_ADDRESSER = "mail.addresser";

        /**
         * 邮箱服务器ssl端口
         */
        public static final String MAIL_SSL = "mail.ssl";

    }
}
