package com.iss.sdb.pet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iss.sdb.commons.logs.OperateLog;
import com.iss.sdb.commons.logs.OperateVerb;
import com.iss.sdb.pet.dao.TLinesMapper;
import com.iss.sdb.pet.models.TLines;
import com.iss.sdb.pet.pojo.Page;

/**
 * 线路管理 服务层实现
 * 
 * @date 2019-03-22
 */
@Service
public class TLinesServiceImpl
{
	@Autowired
	private TLinesMapper tLinesMapper;
	
	
	/**
     * 方法描述：分页查询线路管理信息列表
     * @since 2019-03-22
     */
    public List<TLines> findPage(Page page) {
        List<TLines> result = tLinesMapper.findPage(page);
        return result;

    }

    /**
     * 
     * 查询所有线路管理信息
     * @since 2019-03-22
     */
    public List<TLines> findList(TLines tLines) {
        List<TLines> result = tLinesMapper.findList(tLines);
        return result;

    }

    /**
     * 查询线路管理信息
     * @since 2019-03-22
     */
    public TLines get(Long id) {
        TLines result = tLinesMapper.get(id);
        return result;
    }

    /**
     * 
     * 根据标识ID获取线路管理信息
     * @since 2019-03-22
     */
    public TLines getTLines(TLines tLines) {
        TLines result = tLinesMapper.getTLines(tLines);
        return result;
    }

    /**
     * 方法描述：添加线路管理信息
     * @since 2019-03-22
     */
    @OperateLog(verb = OperateVerb.ADD, noun = "新增线路管理")
    public int add(TLines tLines) {
        int result = tLinesMapper.add(tLines);
        return result;
    }

    /**
     * 方法描述：更新线路管理信息
     * @since 2019-03-22
     */
    @OperateLog(verb = OperateVerb.MODIFY, noun = "修改线路管理信息")
    public int update(TLines tLines) {
        int result = tLinesMapper.update(tLines);
        return result;

    }

    /**
     * 方法描述：删除线路管理信息
     * @since 2019-03-22
     */
    @OperateLog(verb = OperateVerb.DEL, noun = "删除线路管理信息")
    public int delete(TLines tLines) {
        int result = tLinesMapper.delete(tLines);
        return result;
    }
}
