package com.iss.sdb.pet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iss.sdb.commons.logs.OperateLog;
import com.iss.sdb.commons.logs.OperateVerb;
import com.iss.sdb.pet.dao.TNewsMapper;
import com.iss.sdb.pet.models.TNews;
import com.iss.sdb.pet.pojo.Page;

/**
 * 新闻管理 服务层实现
 * 
 * @date 2019-03-22
 */
@Service
public class TNewsServiceImpl
{
	@Autowired
	private TNewsMapper tNewsMapper;
	
	
	/**
     * 方法描述：分页查询新闻管理信息列表
     * @since 2019-03-22
     */
    public List<TNews> findPage(Page page) {
        List<TNews> result = tNewsMapper.findPage(page);
        return result;

    }

    /**
     * 
     * 查询所有新闻管理信息
     * @since 2019-03-22
     */
    public List<TNews> findList(TNews tNews) {
        List<TNews> result = tNewsMapper.findList(tNews);
        return result;

    }

    /**
     * 查询新闻管理信息
     * @since 2019-03-22
     */
    public TNews get(Long id) {
        TNews result = tNewsMapper.get(id);
        return result;
    }

    /**
     * 
     * 根据标识ID获取新闻管理信息
     * @since 2019-03-22
     */
    public TNews getTNews(TNews tNews) {
        TNews result = tNewsMapper.getTNews(tNews);
        return result;
    }

    /**
     * 方法描述：添加新闻管理信息
     * @since 2019-03-22
     */
    @OperateLog(verb = OperateVerb.ADD, noun = "新增新闻管理")
    public int add(TNews tNews) {
        int result = tNewsMapper.add(tNews);
        return result;
    }

    /**
     * 方法描述：更新新闻管理信息
     * @since 2019-03-22
     */
    @OperateLog(verb = OperateVerb.MODIFY, noun = "修改新闻管理信息")
    public int update(TNews tNews) {
        int result = tNewsMapper.update(tNews);
        return result;

    }

    /**
     * 方法描述：删除新闻管理信息
     * @since 2019-03-22
     */
    @OperateLog(verb = OperateVerb.DEL, noun = "删除新闻管理信息")
    public int delete(TNews tNews) {
        int result = tNewsMapper.delete(tNews);
        return result;
    }
}
