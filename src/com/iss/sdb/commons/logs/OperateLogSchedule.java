/**
 * OperateLogSchedule.java 2016年9月24日
 * 
 * Copyright 2001-2016  All rights reserved.
 *  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.commons.logs;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.iss.sdb.commons.logs.dao.LogMapper;
import com.iss.sdb.commons.models.LogModel;

/**
 * 操作日志记录定时任务
 * 
 * @author hqsunc
 * @since 2016年9月24日
 *
 */
@Component
@EnableScheduling
public class OperateLogSchedule {

    /**
     * logger
     */
    private Log logger = LogFactory.getLog(OperateLogSchedule.class);

    /**
     * 操作日志模型队列
     */
    private ConcurrentLinkedQueue<LogModel> queue = new ConcurrentLinkedQueue<LogModel>();

    /**
     * DB Log Mapper
     */
    @Autowired
    private LogMapper logMapper;

    /**
     * 定时记录操作日志
     *
     * @author hqsunc
     * @since 2016年9月25日
     */
    @Scheduled(fixedDelay = 3000)
    public void save() {
        if (!queue.isEmpty()) {
            LogModel log = queue.poll();
            logger.info(log.toString());
            logMapper.add(log);
        }
    }

    /**
     * @return the queue
     * @see OperateLogSchedule#queue
     */
    public ConcurrentLinkedQueue<LogModel> getQueue() {
        return queue;
    }

}
