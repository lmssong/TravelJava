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
import com.iss.sdb.pet.models.TLines;
import com.iss.sdb.pet.pojo.AjaxResponse;
import com.iss.sdb.pet.pojo.Page;
import com.iss.sdb.pet.service.TLinesServiceImpl;



/**
 * 线路管理 信息操作处理
 * 
 * @date 2019-03-22
 */
@Controller
@SessionAttributes(Constants.Commons.SESSION_STORE_USER)
@RequestMapping("/m/tLines")
public class TLinesController
{
    private String prefix = "mng/tLines";
	
	@Autowired
	private TLinesServiceImpl tLinesService;
	
	/**
	 * 文件上传
	 */
	@Autowired
	private FileUploadSupport fileUpload;
	
	@RequestMapping(method = RequestMethod.GET, path = "/list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView(prefix + "/tLines_list");
		return mv;
	}
	
	/**
	 * 跳转线路管理新增页
	 * @return
	 * @since 2019-03-22
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/add")
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView(prefix + "/tLines_edit");
		mv.addObject("tLines", new TLines());
		return mv;
	}

	/**
	 * 根据线路管理ID查询单条数据
	 * @return 
	 * @since 2019-03-22
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/get/{id:\\d+}")
	public ModelAndView get(@PathVariable(value = "id") Long id) {
		TLines tLines = tLinesService.get(id);
		ModelAndView mv = new ModelAndView(prefix + "/tLines_details");
		mv.addObject("tLines", tLines);
		return mv;
	}

	/**
	 * 方法描述：跳转线路管理编辑页
	 * @param id
	 * @return
	 * @since 2019-03-22
	 */

	@RequestMapping(method = RequestMethod.GET, path = "/edit/{id:\\d+}")
	public ModelAndView edit(@PathVariable(value = "id") Long id) {
		TLines tLines = tLinesService.get(id);
		ModelAndView mv = new ModelAndView(prefix + "/tLines_edit");
		mv.addObject("tLines", tLines);
		return null != tLines ? mv : this.list();
	}

	/**
	 * 方法描述：添加线路管理信息
	 * @since 2019-03-22
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/create")
	@ResponseBody
	public String add(TLines tLines) {
		String aj;
		int result = tLinesService.add(tLines);
		if (result > 0) {
			aj = AjaxResponse.fromCode(result).toJSONString();
		} else {
			aj = AjaxResponse.fromCode(result).toJSONString();
		}
		return aj;

	}

	/**
	 * 方法描述：删除线路管理信息
	 * @since 2019-03-22
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/delete")
	@ResponseBody
	public String delete(TLines tLines) {
		String aj;
		int result = tLinesService.delete(tLines);
		if (result > 0) {
			aj = AjaxResponse.fromCode(result).toJSONString();
		} else {
			aj = AjaxResponse.fromCode(result).toJSONString();
		}
		return aj;

	}

	/**
	 * 方法描述：更新线路管理信息
	 * @since 2019-03-22
	 * @see
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/update")
	@ResponseBody
	public String update(TLines tLines) {
		String aj;
		int result = tLinesService.update(tLines);
		if (result > 0) {
			aj = AjaxResponse.fromCode(result).toJSONString();
		} else {
			aj = AjaxResponse.fromCode(result).toJSONString();
		}
		return aj;
	}

	/**
	 * 方法描述：线路管理信息分页查询
	 * @since 2019-03-22
	 * @see
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/findPage")
	@ResponseBody
	public String findPage(TLines tLines, Page page) {
		page.setSearchParam(tLines);
		tLinesService.findPage(page);
		String aj = AjaxResponse.fromData(page).toJSONString();
		return aj;

	}

	/**
	 * 
	 * 方法描述：线路管理信息查询
	 * @since 2017年3月23日
	 * @see
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/findList")
	@ResponseBody
	public String findList(TLines tLines) {
		List<TLines> list = tLinesService.findList(tLines);
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
