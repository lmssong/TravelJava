/**
 * ExamConfig.java 2016年7月28日
 * 
 * Copyright 2001-2016  All rights reserved.
 *  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.pet.commons;

import java.util.HashMap;
import java.util.Map;

/**
 * 题型和难度配置
 * 
 * @author hqsunc
 * @since 2016年7月28日
 * @see [Class/Method]
 *
 */
public final class ExamConfig {
    
    /**
     * 提醒Map
     */
    public static final Map<String, Integer > QUESTIONS_MAP = new HashMap<String, Integer>(){
        /**
         * serialVersionUID
         */
        private static final long serialVersionUID = 1L;
        {
            put("单选题", 1) ;
            put("多选题", 2) ;
            put("判断题", 3) ;
        }
    };
    
    /**
     * 难度系数Map
     */
    public static final Map<String, Integer > QUESTIONS_TYPE = new HashMap<String, Integer>(){
        /**
         * serialVersionUID
         */
        private static final long serialVersionUID = 1L;
        {
            put("容易", 1) ;
            put("较容易", 2) ;
            put("中", 3) ;
            put("难", 4) ;
            put("较难", 5) ;
        }
    };
}
