/**
 * BannerMapper.java 2016年9月20日
 * 
 * Copyright 2001-2016 All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.pet.dao;

import java.util.List;

import com.iss.sdb.pet.models.UserModel;
import com.iss.sdb.pet.pojo.Page;

/**
 * UserMapper
 * 
 * @author hqsunc
 * @since 2016年10月11日
 *
 */
public interface UserMapper {

    /**
     * 
     * 分页查询用户信息
     *
     * @author hqsunc
     * @param page
     *            分页模型
     * @return 用户分页列表
     * @since 2016年11月18日
     */
    public List<UserModel> findPage(Page page);
    
    
    /**
     * 
     * 查询所有用户
     *
     * @author hqsunc
     * @return
     * @since 2017年3月23日
     * @see
     */
    public List<UserModel> findList(UserModel userModel);
    

    /**
     * 查询单个用户信息
     *
     * @author hqsunc
     * @param id
     *            用户标识符
     * @return 用户Model对象
     * @since 2016年10月11日
     */
    public UserModel get(long id);

    /**
     * 查询单个用户信息
     *
     * @author hqsunc
     * @param id
     *            用户标识符
     * @return 用户Model对象
     * @since 2016年10月11日
     */
    public UserModel getUser(UserModel userModel);

    /**
     * 用户登录
     * 
     * @author hqsunc
     * @param userModel
     * @return
     * @since 2016年10月28日
     * @see
     */
    public UserModel login(UserModel userModel);

    /**
     * 根据条件查询列表信息
     *
     * @author hqsunc
     * @param page
     *            分页对象模型
     * @return 用户分页列表
     * @since 2016年10月11日
     */
    public List<UserModel> findList(Page page);

    /**
     * 根据条件获取所有用户信息列表
     * 
     * @return
     */
    public List<UserModel> findAll();

    /**
     * 新增用户
     *
     * @author hqsunc
     * @param userModel
     *            用户模型
     * @return 持久化操作返回码
     * @since 2016年10月11日
     */
    public int add(UserModel userModel);

    /**
     * 更新用户信息
     *
     * @author hqsunc
     * @param userModel
     *            用户模型
     * @return 持久化操作返回码
     * @since 2016年9月22日
     */
    public int update(UserModel userModel);

    /**
     * 
     * 删除用户信息
     *
     * @author hqsunc
     * @param userModel
     * @return
     * @since 2017年3月7日
     * @see
     */
    public int delete(UserModel userModel);

    /**
     * 
     * 检查名称唯一性
     *
     * @author hqsunc
     * @param userModel
     * @return
     * @since 2017年3月7日
     * @see
     */
    public UserModel checkAccount(UserModel userModel);
}
