package com.iss.sdb.pet.dao;

import com.iss.sdb.pet.models.TNews;
import com.iss.sdb.pet.pojo.Page;
import java.util.List;	

/**
 * 新闻管理 数据层
 * 
 * @date 2019-03-22
 */
public interface TNewsMapper 
{
	/**
     * 
     * 分页查询活动信息
     * @since 2019-03-22
     */
    public List<TNews> findPage(Page page);
    
    
    /**
     * 
     * 查询所有活动
     * @since 2019-03-22
     */
    public List<TNews> findList(TNews tNews);
    

    /**
     * 查询单个活动信息
     * @since 2019-03-22
     */
    public TNews get(long id);

    /**
     * 查询单个活动信息
     * @since 2019-03-22
     */
    public TNews getTNews(TNews tNews);

    /**
     * 根据条件获取所有活动信息列表
     * @since 2019-03-22
     */
    public List<TNews> findAll();

    /**
     * 新增活动
     * @since 2019-03-22
     */
    public int add(TNews tNews);

    /**
     * 更新活动信息
     * @since 2019-03-22
     */
    public int update(TNews tNews);

    /**
     * 删除活动信息
     * @since 2019-03-22
     */
    public int delete(TNews tNews);
}