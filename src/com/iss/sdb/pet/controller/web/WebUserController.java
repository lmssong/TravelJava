/**
 * UserController.java 2016年12月22日
 * 
 * Copyright 2001-2016 All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.iss.sdb.pet.controller.web;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.iss.sdb.pet.commons.Constants;
import com.iss.sdb.pet.models.UserModel;
import com.iss.sdb.pet.pojo.AjaxResponse;
import com.iss.sdb.pet.service.UserService;

/**
 * 用户 Controller
 * 
 * 
 * @author peiwuc
 * @since 2016年12月22日
 * @see [Class/Method]
 * 
 */
@Controller
@SessionAttributes(Constants.Commons.SESSION_WEB_USER)
@RequestMapping("w/user")
public class WebUserController {
	/**
	 * 日志系统
	 */
	private Log logger = LogFactory.getLog(WebUserController.class);

	/**
	 * 用户Service
	 */
	@Autowired
	private UserService userService;


	@RequestMapping(path = "/info")
	public ModelAndView init(HttpSession session) {
		ModelAndView mv = new ModelAndView("web/personal/detail");

		return mv;
	}

	/**
	 * 方法描述：添加用户信息
	 * <p>
	 * POST / user / create
	 * 
	 * @author hqsunc
	 * @param user
	 *            用户信息模型
	 * @param userSession
	 *            . 用户会话数据
	 * @return 持久化操作码
	 * @since 2016年11月18日
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/create")
	@ResponseBody
	public String add(UserModel user) {
		logger.debug("UserController add begin" + user);
		user.setRole(2);
		String aj;
		int result = userService.add(user);
		if (result > 0) {
			aj = AjaxResponse.fromCode(result).toJSONString();
		} else {
			aj = AjaxResponse.fromCode(result).toJSONString();
		}

		logger.debug("UserController add end " + aj);

		return aj;

	}

	/**
	 * 
	 * <summary> <description>
	 * 
	 * @author hqsunc
	 * @param user
	 * @return
	 * @since 2017年4月23日
	 * @see
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/update")
	@ResponseBody
	public String update(UserModel user, HttpSession session) {
		logger.debug("UserController update begin" + user);
		UserModel curUser = (UserModel) session.getAttribute(Constants.Commons.SESSION_WEB_USER);
		curUser.setIntegral(curUser.getIntegral() + user.getIntegral());
		String aj;
		int result = userService.update(curUser);
		if (result > 0) {

			session.setAttribute(Constants.Commons.SESSION_WEB_USER, curUser);

			aj = AjaxResponse.fromCode(result).toJSONString();
		} else {
			aj = AjaxResponse.fromCode(result).toJSONString();
		}

		logger.debug("UserController update end" + aj);

		return aj;
	}

	/**
	 * 
	 * 检查用户名是否已存在
	 * 
	 * @author hqsunc
	 * @param user
	 * @return
	 * @since 2017年3月7日
	 * @see
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/checkAccount")
	@ResponseBody
	public String checkNameEml(UserModel user) {
		logger.debug("[WebUserCtrl.activation] begin...");
		UserModel userModel = userService.checkAccount(user);
		logger.debug("[WebUserCtrl.activation] begin...");
		return "{\"valid\":" + (null != userModel ? false : true) + "}";
	}
}
