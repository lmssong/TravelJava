package com.iss.sdb.pet.dao;

import com.iss.sdb.pet.models.TMessage;
import com.iss.sdb.pet.pojo.Page;
import java.util.List;	

/**
 * 留言管理 数据层
 * 
 * @date 2019-03-22
 */
public interface TMessageMapper 
{
	/**
     * 
     * 分页查询活动信息
     * @since 2019-03-22
     */
    public List<TMessage> findPage(Page page);
    
    
    /**
     * 
     * 查询所有活动
     * @since 2019-03-22
     */
    public List<TMessage> findList(TMessage tMessage);
    

    /**
     * 查询单个活动信息
     * @since 2019-03-22
     */
    public TMessage get(long id);

    /**
     * 查询单个活动信息
     * @since 2019-03-22
     */
    public TMessage getTMessage(TMessage tMessage);

    /**
     * 根据条件获取所有活动信息列表
     * @since 2019-03-22
     */
    public List<TMessage> findAll();

    /**
     * 新增活动
     * @since 2019-03-22
     */
    public int add(TMessage tMessage);

    /**
     * 更新活动信息
     * @since 2019-03-22
     */
    public int update(TMessage tMessage);

    /**
     * 删除活动信息
     * @since 2019-03-22
     */
    public int delete(TMessage tMessage);
}