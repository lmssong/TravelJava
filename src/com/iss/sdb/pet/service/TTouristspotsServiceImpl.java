package com.iss.sdb.pet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iss.sdb.commons.logs.OperateLog;
import com.iss.sdb.commons.logs.OperateVerb;
import com.iss.sdb.pet.dao.TTouristspotsMapper;
import com.iss.sdb.pet.models.TTouristspots;
import com.iss.sdb.pet.pojo.Page;

/**
 * 景点管理 服务层实现
 * 
 * @date 2019-03-22
 */
@Service
public class TTouristspotsServiceImpl
{
	@Autowired
	private TTouristspotsMapper tTouristspotsMapper;
	
	
	/**
     * 方法描述：分页查询景点管理信息列表
     * @since 2019-03-22
     */
    public List<TTouristspots> findPage(Page page) {
        List<TTouristspots> result = tTouristspotsMapper.findPage(page);
        return result;

    }

    /**
     * 
     * 查询所有景点管理信息
     * @since 2019-03-22
     */
    public List<TTouristspots> findList(TTouristspots tTouristspots) {
        List<TTouristspots> result = tTouristspotsMapper.findList(tTouristspots);
        return result;

    }

    /**
     * 查询景点管理信息
     * @since 2019-03-22
     */
    public TTouristspots get(Long id) {
        TTouristspots result = tTouristspotsMapper.get(id);
        return result;
    }

    /**
     * 
     * 根据标识ID获取景点管理信息
     * @since 2019-03-22
     */
    public TTouristspots getTTouristspots(TTouristspots tTouristspots) {
        TTouristspots result = tTouristspotsMapper.getTTouristspots(tTouristspots);
        return result;
    }

    /**
     * 方法描述：添加景点管理信息
     * @since 2019-03-22
     */
    @OperateLog(verb = OperateVerb.ADD, noun = "新增景点管理")
    public int add(TTouristspots tTouristspots) {
        int result = tTouristspotsMapper.add(tTouristspots);
        return result;
    }

    /**
     * 方法描述：更新景点管理信息
     * @since 2019-03-22
     */
    @OperateLog(verb = OperateVerb.MODIFY, noun = "修改景点管理信息")
    public int update(TTouristspots tTouristspots) {
        int result = tTouristspotsMapper.update(tTouristspots);
        return result;

    }

    /**
     * 方法描述：删除景点管理信息
     * @since 2019-03-22
     */
    @OperateLog(verb = OperateVerb.DEL, noun = "删除景点管理信息")
    public int delete(TTouristspots tTouristspots) {
        int result = tTouristspotsMapper.delete(tTouristspots);
        return result;
    }
}
