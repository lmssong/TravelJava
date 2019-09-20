/**
 * RoleController.java 2016年9月19日
 * 
 * Copyright 2001-2016 All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.pet.controller.manage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.iss.sdb.commons.utils.EncryptUtil;
import com.iss.sdb.pet.commons.Constants;
import com.iss.sdb.pet.models.UserModel;
import com.iss.sdb.pet.pojo.AjaxResponse;
import com.iss.sdb.pet.service.UserService;

/**
 * Main Controller
 * 
 * @author hqsunc
 * @since 2016年10月12日
 *
 */
@Controller
@RequestMapping("/m")
public class MainController {
    /**
     * 日志系统
     */
    private Log logger = LogFactory.getLog(MainController.class);

    @Autowired
    private UserService userService;

    /**
     * 跳转的主页
     * 
     * @author hqsunc
     * @return
     * @since 2016年10月17日
     * @see
     */
    @RequestMapping(path = "/index")
    public ModelAndView init() {
        ModelAndView mv = new ModelAndView("mng/index");
        return mv;
    }

    /**
     * 
     * 登录信息验证
     * <p>
     * post /mng/auth/login
     * 
     * @author hqsunc
     * @param user
     *            用户模型
     * @param session
     *            会话模型
     * @since 2016年12月20日
     * @see
     */
    @RequestMapping(method = RequestMethod.POST, path = "/login")
    @ResponseBody
    public String login(UserModel user, HttpSession session, HttpServletRequest request, HttpServletResponse response,
            @RequestParam("redirect") String redirect) {
        logger.debug("MngLoginCtrl login begin" + user + ", redirect=" + redirect);
        AjaxResponse ajax = new AjaxResponse(true);
        String password = EncryptUtil.md5(user.getPassword());
        user.setPassword(password);
        UserModel userModel = userService.getUser(user);
        if (userModel != null && userModel.getPassword().equals(user.getPassword())) {
            session.setAttribute(Constants.Commons.SESSION_STORE_USER, userModel);
        }
        else {
            ajax.toError();
        }
        logger.debug("MngLoginCtrl login end" + user);
        return AjaxResponse.fromData(ajax).toJSONString();
    }
    
    @RequestMapping(method = RequestMethod.GET, path = "/logout")
    public ModelAndView logout(HttpSession session, HttpServletRequest request, SessionStatus sessionStatus) {
        session.removeAttribute(Constants.Commons.SESSION_WEB_USER);
        ModelAndView mv = new ModelAndView("mng/index");

        return mv;
    }

}
