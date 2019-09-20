package com.iss.sdb.pet.dao;

import com.iss.sdb.pet.models.TLines;
import com.iss.sdb.pet.pojo.Page;
import java.util.List;	

/**
 * 线路管理 数据层
 * 
 * @date 2019-03-22
 */
public interface TLinesMapper 
{
	/**
     * 
     * 分页查询活动信息
     * @since 2019-03-22
     */
    public List<TLines> findPage(Page page);
    
    
    /**
     * 
     * 查询所有活动
     * @since 2019-03-22
     */
    public List<TLines> findList(TLines tLines);
    

    /**
     * 查询单个活动信息
     * @since 2019-03-22
     */
    public TLines get(long id);

    /**
     * 查询单个活动信息
     * @since 2019-03-22
     */
    public TLines getTLines(TLines tLines);

    /**
     * 根据条件获取所有活动信息列表
     * @since 2019-03-22
     */
    public List<TLines> findAll();

    /**
     * 新增活动
     * @since 2019-03-22
     */
    public int add(TLines tLines);

    /**
     * 更新活动信息
     * @since 2019-03-22
     */
    public int update(TLines tLines);

    /**
     * 删除活动信息
     * @since 2019-03-22
     */
    public int delete(TLines tLines);
}