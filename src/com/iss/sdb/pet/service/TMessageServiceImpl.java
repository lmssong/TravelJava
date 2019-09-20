package com.iss.sdb.pet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iss.sdb.commons.logs.OperateLog;
import com.iss.sdb.commons.logs.OperateVerb;
import com.iss.sdb.pet.dao.TMessageMapper;
import com.iss.sdb.pet.models.TMessage;
import com.iss.sdb.pet.pojo.Page;

/**
 * 留言管理 服务层实现
 * 
 * @date 2019-03-22
 */
@Service
public class TMessageServiceImpl
{
	@Autowired
	private TMessageMapper tMessageMapper;
	
	
	/**
     * 方法描述：分页查询留言管理信息列表
     * @since 2019-03-22
     */
    public List<TMessage> findPage(Page page) {
        List<TMessage> result = tMessageMapper.findPage(page);
        return result;

    }

    /**
     * 
     * 查询所有留言管理信息
     * @since 2019-03-22
     */
    public List<TMessage> findList(TMessage tMessage) {
        List<TMessage> result = tMessageMapper.findList(tMessage);
        return result;

    }

    /**
     * 查询留言管理信息
     * @since 2019-03-22
     */
    public TMessage get(Long id) {
        TMessage result = tMessageMapper.get(id);
        return result;
    }

    /**
     * 
     * 根据标识ID获取留言管理信息
     * @since 2019-03-22
     */
    public TMessage getTMessage(TMessage tMessage) {
        TMessage result = tMessageMapper.getTMessage(tMessage);
        return result;
    }

    /**
     * 方法描述：添加留言管理信息
     * @since 2019-03-22
     */
    @OperateLog(verb = OperateVerb.ADD, noun = "新增留言管理")
    public int add(TMessage tMessage) {
        int result = tMessageMapper.add(tMessage);
        return result;
    }

    /**
     * 方法描述：更新留言管理信息
     * @since 2019-03-22
     */
    @OperateLog(verb = OperateVerb.MODIFY, noun = "修改留言管理信息")
    public int update(TMessage tMessage) {
        int result = tMessageMapper.update(tMessage);
        return result;

    }

    /**
     * 方法描述：删除留言管理信息
     * @since 2019-03-22
     */
    @OperateLog(verb = OperateVerb.DEL, noun = "删除留言管理信息")
    public int delete(TMessage tMessage) {
        int result = tMessageMapper.delete(tMessage);
        return result;
    }
}
