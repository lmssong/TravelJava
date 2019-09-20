/**
 * LogAspect.java 2016年9月6日
 * 
 * Copyright 2001-2016  All rights reserved.
 *  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.commons.logs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.iss.sdb.commons.models.LogModel;
import com.iss.sdb.pet.commons.Constants;
import com.iss.sdb.pet.models.UserModel;

/**
 * 操作日志AOP实现类
 * 
 * @author hqsunc
 * @since 2016年9月6日
 *
 */
@Aspect
@Component
public class OperateLogAspect {

    /**
     * 操作日志记录定时任务
     */
    @Autowired
    private OperateLogSchedule operateLogSchedule;

    /**
     * 记录操作日志
     *
     * @author hqsunc
     * @param operateLog
     * @throws Throwable
     * @since 2016年9月13日
     */
    @AfterReturning(pointcut = "@annotation(operateLog)", argNames = "operateLog")
    public void operate(OperateLog operateLog) throws Throwable {

        UserModel user = null;
        String clientIp = "";

        RequestAttributes ras = RequestContextHolder.getRequestAttributes();

        if (null != ras) {
            HttpServletRequest request = ((ServletRequestAttributes) ras).getRequest();

            clientIp = request.getRemoteAddr();

            HttpSession session = request.getSession();
            if (null != session) {
                Object sessionObj = session.getAttribute(Constants.Commons.SESSION_STORE_USER);
                if (sessionObj instanceof UserModel) {
                    user = (UserModel) sessionObj;
                }
            }
        }

        // 组装操作日志对象，用于日志文件和数据库记录
        LogModel log = new LogModel();

        if (null != user) {
            log.setUserId(user.getId());
            log.setUserAccount(user.getUserName());
            log.setUserName(user.getName());
        }
        log.setClientIp(clientIp);
        log.setIdx(operateLog.verb().getIdx());
        log.setVerb(operateLog.verb().getValue());
        log.setNoun(operateLog.noun());

        operateLogSchedule.getQueue().offer(log);
    }

}
