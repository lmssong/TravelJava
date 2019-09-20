/**
 * UserService.java 2016年9月19日
 * 
 * Copyright 2001-2016 All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.pet.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iss.sdb.commons.logs.OperateLog;
import com.iss.sdb.commons.logs.OperateVerb;
import com.iss.sdb.commons.utils.EncryptUtil;
import com.iss.sdb.pet.dao.UserMapper;
import com.iss.sdb.pet.models.UserModel;
import com.iss.sdb.pet.pojo.Page;

/**
 * UserService
 * 
 * @author hqsunc
 * @since 2016年10月12日
 *
 */
@Service
@Transactional
public class UserService {
    /**
     * 日志系统
     */
    private Log logger = LogFactory.getLog(UserService.class);

    /**
     * UserMapper
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 方法描述：分页查询用户信息列表
     *
     * @author hqsunc
     * @param page
     *            分页模型
     * @return 用户信息列表
     * @since 2016年11月18日
     */
    public List<UserModel> findPage(Page page) {
        logger.debug("UserService findPage begin" + page);

        List<UserModel> result = userMapper.findPage(page);

        logger.debug("UserService findPage end" + result);

        return result;

    }

    /**
     * 
     * 查询所有用户信息
     *
     * @author hqsunc
     * @param user
     * @return
     * @since 2017年3月23日
     * @see
     */
    public List<UserModel> findList(UserModel user) {
        logger.debug("UserService findList begin" + user);

        List<UserModel> result = userMapper.findList(user);

        logger.debug("UserService findList end" + result);

        return result;

    }

    /**
     * 
     * 根据标识ID获取用户信息
     *
     * @author hqsunc
     * @param id
     * @return
     * @since 2017年3月7日
     * @see
     */
    public UserModel get(Long id) {
        logger.debug("UserService get begin " + id);

        UserModel result = userMapper.get(id);

        logger.debug("UserService get end" + result);

        return result;
    }

    /**
     * 
     * 根据标识ID获取用户信息
     *
     * @author hqsunc
     * @param id
     * @return
     * @since 2017年3月7日
     * @see
     */
    public UserModel getUser(UserModel user) {
        logger.debug("UserService getUser begin " + user);

        UserModel result = userMapper.getUser(user);

        logger.debug("UserService get getUser" + result);

        return result;
    }

    /**
     * 方法描述：添加用户信息
     *
     * @author hqsunc
     * @param user
     *            用户信息
     * @return 持久化操作码
     * @since 2016年11月18日
     * @see
     */
    @OperateLog(verb = OperateVerb.ADD, noun = "新增用户")
    public int add(UserModel user) {
        logger.debug("UserService add begin " + user);
        user.setPassword(EncryptUtil.md5(user.getPassword()));

        int result = userMapper.add(user);
        logger.debug("UserService add end" + result);

        return result;
    }

    /**
     * 方法描述：更新用户信息
     *
     * @author hqsunc
     * @param user
     *            用户信息模型
     * @return 持久化操作码
     * @since 2016年11月18日
     */
    @OperateLog(verb = OperateVerb.MODIFY, noun = "修改用户信息")
    public int update(UserModel user) {
        logger.debug("UserService update begin" + user);
        int result = userMapper.update(user);
        logger.debug("UserService update end" + result);
        return result;

    }

    /**
     * 方法描述：删除用户信息
     *
     * @author hqsunc
     * @param id
     *            用户ID
     * @return 持久化操作码
     * @since 2016年11月18日
     */
    @OperateLog(verb = OperateVerb.DEL, noun = "删除用户信息")
    public int delete(UserModel user) {
        logger.debug("UserService delete begin " + user);
        int result = userMapper.delete(user);
        logger.debug("UserService delete end " + result);
        return result;
    }

    /**
     * 
     * <summary>
     * <description>
     *
     * @author hqsunc
     * @param user
     * @return
     * @since 2017年3月7日
     * @see
     */
    @OperateLog(verb = OperateVerb.MODIFY, noun = "校验账号是否唯一")
    public UserModel checkAccount(UserModel user) {
        logger.debug("UserService checkNameEml start...");
        logger.info("UserService checkNameEml user:" + user.toString());
        UserModel result = userMapper.checkAccount(user);
        logger.debug("UserService checkNameEml end...");
        return result;
    }

}
