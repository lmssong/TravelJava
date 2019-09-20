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
import com.iss.sdb.pet.models.THotel;
import com.iss.sdb.pet.pojo.AjaxResponse;
import com.iss.sdb.pet.pojo.Page;
import com.iss.sdb.pet.service.THotelServiceImpl;



/**
 * 酒店管理 信息操作处理
 * 
 * @date 2019-03-22
 */
@Controller
@SessionAttributes(Constants.Commons.SESSION_STORE_USER)
@RequestMapping("/m/tHotel")
public class THotelController
{
    private String prefix = "mng/tHotel";
	
	@Autowired
	private THotelServiceImpl tHotelService;
	
	/**
	 * 文件上传
	 */
	@Autowired
	private FileUploadSupport fileUpload;
	
	@RequestMapping(method = RequestMethod.GET, path = "/list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView(prefix + "/tHotel_list");
		return mv;
	}
	
	/**
	 * 跳转酒店管理新增页
	 * @return
	 * @since 2019-03-22
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/add")
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView(prefix + "/tHotel_edit");
		mv.addObject("tHotel", new THotel());
		return mv;
	}

	/**
	 * 根据酒店管理ID查询单条数据
	 * @return 
	 * @since 2019-03-22
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/get/{id:\\d+}")
	public ModelAndView get(@PathVariable(value = "id") Long id) {
		THotel tHotel = tHotelService.get(id);
		ModelAndView mv = new ModelAndView(prefix + "/tHotel_details");
		mv.addObject("tHotel", tHotel);
		return mv;
	}

	/**
	 * 方法描述：跳转酒店管理编辑页
	 * @param id
	 * @return
	 * @since 2019-03-22
	 */

	@RequestMapping(method = RequestMethod.GET, path = "/edit/{id:\\d+}")
	public ModelAndView edit(@PathVariable(value = "id") Long id) {
		THotel tHotel = tHotelService.get(id);
		ModelAndView mv = new ModelAndView(prefix + "/tHotel_edit");
		mv.addObject("tHotel", tHotel);
		return null != tHotel ? mv : this.list();
	}

	/**
	 * 方法描述：添加酒店管理信息
	 * @since 2019-03-22
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/create")
	@ResponseBody
	public String add(THotel tHotel) {
		String aj;
		int result = tHotelService.add(tHotel);
		if (result > 0) {
			aj = AjaxResponse.fromCode(result).toJSONString();
		} else {
			aj = AjaxResponse.fromCode(result).toJSONString();
		}
		return aj;

	}

	/**
	 * 方法描述：删除酒店管理信息
	 * @since 2019-03-22
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/delete")
	@ResponseBody
	public String delete(THotel tHotel) {
		String aj;
		int result = tHotelService.delete(tHotel);
		if (result > 0) {
			aj = AjaxResponse.fromCode(result).toJSONString();
		} else {
			aj = AjaxResponse.fromCode(result).toJSONString();
		}
		return aj;

	}

	/**
	 * 方法描述：更新酒店管理信息
	 * @since 2019-03-22
	 * @see
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/update")
	@ResponseBody
	public String update(THotel tHotel) {
		String aj;
		int result = tHotelService.update(tHotel);
		if (result > 0) {
			aj = AjaxResponse.fromCode(result).toJSONString();
		} else {
			aj = AjaxResponse.fromCode(result).toJSONString();
		}
		return aj;
	}

	/**
	 * 方法描述：酒店管理信息分页查询
	 * @since 2019-03-22
	 * @see
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/findPage")
	@ResponseBody
	public String findPage(THotel tHotel, Page page) {
		page.setSearchParam(tHotel);
		tHotelService.findPage(page);
		String aj = AjaxResponse.fromData(page).toJSONString();
		return aj;

	}

	/**
	 * 
	 * 方法描述：酒店管理信息查询
	 * @since 2017年3月23日
	 * @see
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/findList")
	@ResponseBody
	public String findList(THotel tHotel) {
		List<THotel> list = tHotelService.findList(tHotel);
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
