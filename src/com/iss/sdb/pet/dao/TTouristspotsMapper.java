package com.iss.sdb.pet.dao;

import com.iss.sdb.pet.models.TTouristspots;
import com.iss.sdb.pet.pojo.Page;
import java.util.List;	

/**
 * 景点管理 数据层
 * 
 * @date 2019-03-22
 */
public interface TTouristspotsMapper 
{
	/**
     * 
     * 分页查询活动信息
     * @since 2019-03-22
     */
    public List<TTouristspots> findPage(Page page);
    
    
    /**
     * 
     * 查询所有活动
     * @since 2019-03-22
     */
    public List<TTouristspots> findList(TTouristspots tTouristspots);
    

    /**
     * 查询单个活动信息
     * @since 2019-03-22
     */
    public TTouristspots get(long id);

    /**
     * 查询单个活动信息
     * @since 2019-03-22
     */
    public TTouristspots getTTouristspots(TTouristspots tTouristspots);

    /**
     * 根据条件获取所有活动信息列表
     * @since 2019-03-22
     */
    public List<TTouristspots> findAll();

    /**
     * 新增活动
     * @since 2019-03-22
     */
    public int add(TTouristspots tTouristspots);

    /**
     * 更新活动信息
     * @since 2019-03-22
     */
    public int update(TTouristspots tTouristspots);

    /**
     * 删除活动信息
     * @since 2019-03-22
     */
    public int delete(TTouristspots tTouristspots);
}