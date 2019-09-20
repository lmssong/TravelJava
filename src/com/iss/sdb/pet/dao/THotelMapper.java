package com.iss.sdb.pet.dao;

import com.iss.sdb.pet.models.THotel;
import com.iss.sdb.pet.pojo.Page;

import java.util.List;	

/**
 * 酒店管理 数据层
 * 
 * @date 2019-03-22
 */
public interface THotelMapper 
{
	/**
     * 
     * 分页查询活动信息
     * @since 2019-03-22
     */
    public List<THotel> findPage(Page page);
    
    
    /**
     * 
     * 查询所有活动
     * @since 2019-03-22
     */
    public List<THotel> findList(THotel tHotel);
    

    /**
     * 查询单个活动信息
     * @since 2019-03-22
     */
    public THotel get(long id);

    /**
     * 查询单个活动信息
     * @since 2019-03-22
     */
    public THotel getTHotel(THotel tHotel);

    /**
     * 根据条件获取所有活动信息列表
     * @since 2019-03-22
     */
    public List<THotel> findAll();

    /**
     * 新增活动
     * @since 2019-03-22
     */
    public int add(THotel tHotel);

    /**
     * 更新活动信息
     * @since 2019-03-22
     */
    public int update(THotel tHotel);

    /**
     * 删除活动信息
     * @since 2019-03-22
     */
    public int delete(THotel tHotel);
}