package com.iss.sdb.pet.controller.manage;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.iss.sdb.commons.models.FileUpResModel;
import com.iss.sdb.pet.commons.Constants;
import com.iss.sdb.pet.commons.FileUploadSupport;
import com.iss.sdb.pet.models.TNews;
import com.iss.sdb.pet.pojo.AjaxResponse;
import com.iss.sdb.pet.pojo.Page;
import com.iss.sdb.pet.service.TNewsServiceImpl;



/**
 * 新闻管理 信息操作处理
 * 
 * @date 2019-03-22
 */
@Controller
@SessionAttributes(Constants.Commons.SESSION_STORE_USER)
@RequestMapping("/m/tNews")
public class TNewsController
{
    private String prefix = "mng/tNews";
	
	@Autowired
	private TNewsServiceImpl tNewsService;
	
	/**
	 * 文件上传
	 */
	@Autowired
	private FileUploadSupport fileUpload;
	
	@RequestMapping(method = RequestMethod.GET, path = "/list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView(prefix + "/tNews_list");
		return mv;
	}

	/**
	 * 跳转新闻管理新增页
	 * @return
	 * @since 2019-03-22
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/add")
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView(prefix + "/tNews_edit");
		mv.addObject("tNews", new TNews());
		return mv;
	}

	/**
	 * 根据新闻管理ID查询单条数据
	 * @return 
	 * @since 2019-03-22
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/get/{id:\\d+}")
	public ModelAndView get(@PathVariable(value = "id") Long id) {
		TNews tNews = tNewsService.get(id);
		ModelAndView mv = new ModelAndView(prefix + "/tNews_details");
		mv.addObject("tNews", tNews);
		return mv;
	}

	/**
	 * 方法描述：跳转新闻管理编辑页
	 * @param id
	 * @return
	 * @since 2019-03-22
	 */

	@RequestMapping(method = RequestMethod.GET, path = "/edit/{id:\\d+}")
	public ModelAndView edit(@PathVariable(value = "id") Long id) {
		TNews tNews = tNewsService.get(id);
		ModelAndView mv = new ModelAndView(prefix + "/tNews_edit");
		mv.addObject("tNews", tNews);
		return null != tNews ? mv : this.list();
	}

	/**
	 * 方法描述：添加新闻管理信息
	 * @since 2019-03-22
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/create")
	@ResponseBody
	public String add(TNews tNews) {
		String aj;
		int result = tNewsService.add(tNews);
		if (result > 0) {
			aj = AjaxResponse.fromCode(result).toJSONString();
		} else {
			aj = AjaxResponse.fromCode(result).toJSONString();
		}
		return aj;

	}

	/**
	 * 方法描述：删除新闻管理信息
	 * @since 2019-03-22
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/delete")
	@ResponseBody
	public String delete(TNews tNews) {
		String aj;
		int result = tNewsService.delete(tNews);
		if (result > 0) {
			aj = AjaxResponse.fromCode(result).toJSONString();
		} else {
			aj = AjaxResponse.fromCode(result).toJSONString();
		}
		return aj;

	}

	/**
	 * 方法描述：更新新闻管理信息
	 * @since 2019-03-22
	 * @see
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/update")
	@ResponseBody
	public String update(TNews tNews) {
		String aj;
		int result = tNewsService.update(tNews);
		if (result > 0) {
			aj = AjaxResponse.fromCode(result).toJSONString();
		} else {
			aj = AjaxResponse.fromCode(result).toJSONString();
		}
		return aj;
	}

	/**
	 * 方法描述：新闻管理信息分页查询
	 * @since 2019-03-22
	 * @see
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/findPage")
	@ResponseBody
	public String findPage(TNews tNews, Page page) {
		page.setSearchParam(tNews);
		tNewsService.findPage(page);
		String aj = AjaxResponse.fromData(page).toJSONString();
		return aj;

	}

	/**
	 * 
	 * 方法描述：新闻管理信息查询
	 * @since 2017年3月23日
	 * @see
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/findList")
	@ResponseBody
	public String findList(TNews tNews) {
		List<TNews> list = tNewsService.findList(tNews);
		String aj = AjaxResponse.fromData(list).toJSONString();
		return aj;

	}

	/**
	 * 上传文件
	 * @throws IOException
	 * @since 2016年10月18日
	 * @see
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/upload")
	@ResponseBody
	public FileUpResModel upload(HttpServletRequest request) throws IOException {
		List<FileUpResModel> res = fileUpload.lawResourceFile(request);
		return res.get(0);
	}
}
