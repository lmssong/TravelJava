package com.iss.sdb.pet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iss.sdb.commons.logs.OperateLog;
import com.iss.sdb.commons.logs.OperateVerb;
import com.iss.sdb.pet.dao.THotelMapper;
import com.iss.sdb.pet.models.THotel;
import com.iss.sdb.pet.pojo.Page;

/**
 * 酒店管理 服务层实现
 * 
 * @date 2019-03-22
 */
@Service
public class THotelServiceImpl
{
	@Autowired
	private THotelMapper tHotelMapper;
	
	
	/**
     * 方法描述：分页查询酒店管理信息列表
     * @since 2019-03-22
     */
    public List<THotel> findPage(Page page) {
        List<THotel> result = tHotelMapper.findPage(page);
        return result;

    }

    /**
     * 
     * 查询所有酒店管理信息
     * @since 2019-03-22
     */
    public List<THotel> findList(THotel tHotel) {
        List<THotel> result = tHotelMapper.findList(tHotel);
        return result;

    }

    /**
     * 查询酒店管理信息
     * @since 2019-03-22
     */
    public THotel get(Long id) {
        THotel result = tHotelMapper.get(id);
        return result;
    }

    /**
     * 
     * 根据标识ID获取酒店管理信息
     * @since 2019-03-22
     */
    public THotel getTHotel(THotel tHotel) {
        THotel result = tHotelMapper.getTHotel(tHotel);
        return result;
    }

    /**
     * 方法描述：添加酒店管理信息
     * @since 2019-03-22
     */
    @OperateLog(verb = OperateVerb.ADD, noun = "新增酒店管理")
    public int add(THotel tHotel) {
        int result = tHotelMapper.add(tHotel);
        return result;
    }

    /**
     * 方法描述：更新酒店管理信息
     * @since 2019-03-22
     */
    @OperateLog(verb = OperateVerb.MODIFY, noun = "修改酒店管理信息")
    public int update(THotel tHotel) {
        int result = tHotelMapper.update(tHotel);
        return result;

    }

    /**
     * 方法描述：删除酒店管理信息
     * @since 2019-03-22
     */
    @OperateLog(verb = OperateVerb.DEL, noun = "删除酒店管理信息")
    public int delete(THotel tHotel) {
        int result = tHotelMapper.delete(tHotel);
        return result;
    }
}
