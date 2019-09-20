/**
 * RoleController.java 2016年9月19日
 * 
 * Copyright 2001-2016 All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.pet.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.iss.sdb.commons.utils.EncryptUtil;
import com.iss.sdb.pet.commons.Constants;
import com.iss.sdb.pet.models.THotel;
import com.iss.sdb.pet.models.TLines;
import com.iss.sdb.pet.models.TMessage;
import com.iss.sdb.pet.models.TNews;
import com.iss.sdb.pet.models.TTouristspots;
import com.iss.sdb.pet.models.UserModel;
import com.iss.sdb.pet.pojo.AjaxResponse;
import com.iss.sdb.pet.service.THotelServiceImpl;
import com.iss.sdb.pet.service.TLinesServiceImpl;
import com.iss.sdb.pet.service.TMessageServiceImpl;
import com.iss.sdb.pet.service.TNewsServiceImpl;
import com.iss.sdb.pet.service.TTouristspotsServiceImpl;
import com.iss.sdb.pet.service.UserService;

/**
 * WebMainController
 * 
 * @author hqsunc
 * @since 2016年10月12日
 *
 */
@Controller
@RequestMapping("/w")
public class WebMainController {

    private Log logger = LogFactory.getLog(WebMainController.class);

    @Autowired
    private UserService userService;
    
    @Autowired
	private THotelServiceImpl tHotelService;
    
    @Autowired
	private TTouristspotsServiceImpl tTouristspotsService;
    
    @Autowired
	private TLinesServiceImpl tLinesService;
    
    @Autowired
	private TNewsServiceImpl tNewsService;
    
    @Autowired
	private TMessageServiceImpl tMessageService;

    
    /**
     * 跳转的主页
     * 
     * @return
     * @since 2019年3月22日
     * @see
     */
    @RequestMapping(path = "/index")
    public ModelAndView init() {
        ModelAndView mv = new ModelAndView("web/index");
        mv.addObject("hotelList", tHotelService.findList(new THotel()));
        mv.addObject("touristspotsList", tTouristspotsService.findList(new TTouristspots()));
        return mv;
    }
    
    
    /**
     * 跳转的登陆
     * 
     * @return
     * @since 2019年3月22日
     * @see
     */
    @RequestMapping(path = "/tologin")
    public ModelAndView tologin() {
        ModelAndView mv = new ModelAndView("web/login");
        return mv;
    }
    
    /**
     * 跳转的注册页面
     * 
     * @return
     * @since 2019年3月22日
     * @see
     */
    @RequestMapping(path = "/toreg")
    public ModelAndView toreg() {
        ModelAndView mv = new ModelAndView("web/reg");
        return mv;
    }
    
    
    /**
     * 跳转的旅游景点页面
     * 
     * @return
     * @since 2019年3月22日
     * @see
     */
    @RequestMapping(path = "/touristspots")
    public ModelAndView touristspots() {
        ModelAndView mv = new ModelAndView("web/touristspots");
        mv.addObject("touristspotsList", tTouristspotsService.findList(new TTouristspots()));
        return mv;
    }
    
    
    
    
    /**
     * 跳转的旅游线路页面
     * 
     * @return
     * @since 2019年3月22日
     * @see
     */
    @RequestMapping(path = "/lines")
    public ModelAndView lines(TLines lines) {
        ModelAndView mv = new ModelAndView("web/lines");
        mv.addObject("linesList", tLinesService.findList(lines));
        return mv;
    }
    
    /**
     * 跳转的旅游线路页面
     * 
     * @return
     * @since 2019年3月22日
     * @see
     */
    @RequestMapping(method = RequestMethod.GET, path = "/lineDetail/{id:\\d+}")
    public ModelAndView lineDetail(@PathVariable(value = "id") Long id) {
        ModelAndView mv = new ModelAndView("web/lineDetail");
        mv.addObject("lines", tLinesService.get(id));
        return mv;
    }
    
    
    
    /**
     * 跳转的酒店宾馆页面
     * 
     * @return
     * @since 2019年3月22日
     * @see
     */
    @RequestMapping(path = "/hotels")
    public ModelAndView hotels() {
        ModelAndView mv = new ModelAndView("web/hotels");
        mv.addObject("hotelList", tHotelService.findList(new THotel()));
        return mv;
    }
    
    /**
     * 跳转的我要留言页面
     * 
     * @return
     * @since 2019年3月22日
     * @see
     */
    @RequestMapping(path = "/messages")
    public ModelAndView messages() {
        ModelAndView mv = new ModelAndView("web/messages");
        mv.addObject("messagelList", tMessageService.findList(new TMessage()));
        return mv;
    }
    
    /**
     * 跳转的旅游新闻页面
     * 
     * @return
     * @since 2019年3月22日
     * @see
     */
    @RequestMapping(path = "/news")
    public ModelAndView news() {
        ModelAndView mv = new ModelAndView("web/news");
        mv.addObject("newsList", tNewsService.findList(new TNews()));
        return mv;
    }
    
    /**
     * 跳转的旅游线路页面
     * 
     * @return
     * @since 2019年3月22日
     * @see
     */
    @RequestMapping(method = RequestMethod.GET, path = "/newsDetail/{id:\\d+}")
    public ModelAndView newsDetail(@PathVariable(value = "id") Long id) {
        ModelAndView mv = new ModelAndView("web/newsDetail");
        mv.addObject("news", tNewsService.get(id));
        return mv;
    }
    
    
    /**
	 * 方法描述：添加留言管理信息
	 * @since 2019-03-22
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/addMessage")
	@ResponseBody
	public String addMessage(HttpSession session,TMessage tMessage) {
		String aj;
		
		UserModel userModel=(UserModel)session.getAttribute(Constants.Commons.SESSION_WEB_USER);
		if(userModel==null){
			tMessage.setPerson("游客");
		}else{
			tMessage.setPerson(userModel.getName());
		}
		int result = tMessageService.add(tMessage);
		if (result > 0) {
			aj = AjaxResponse.fromCode(result).toJSONString();
		} else {
			aj = AjaxResponse.fromCode(result).toJSONString();
		}
		return aj;

	}
    

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    @ResponseBody
    public String login(String userName, String password, String code, HttpSession session, HttpServletRequest request,
            HttpServletResponse response) {
        AjaxResponse ajax = new AjaxResponse(true);
        UserModel user = new UserModel();
        user.setPassword(EncryptUtil.md5(password));
        user.setUserName(userName);
        UserModel userModel = userService.getUser(user);
        if (userModel != null && userModel.getPassword().equals(user.getPassword())) {
            if (userModel.getRole() != 2) {
                ajax.setMessage("该用户为管理员");
                ajax.toError();
            }
            else {
                session.setAttribute(Constants.Commons.SESSION_WEB_USER, userModel);
                ajax.toSuccess();
            }

        }
        else {
            ajax.setMessage("用户名或密码错误");
            ajax.toError();
        }
        logger.debug("WebMainController login end" + user);
        return AjaxResponse.fromData(ajax).toJSONString();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/logout")
    public ModelAndView logout(HttpSession session, HttpServletRequest request, SessionStatus sessionStatus) {
        session.removeAttribute(Constants.Commons.SESSION_WEB_USER);
        ModelAndView mv = new ModelAndView("web/index");
        return mv;
    }

    /**
     * 关于我们
     * 
     * @author hqsunc
     * @return
     * @since 2017年1月19日
     * @see
     */
    @RequestMapping(path = "/about")
    public ModelAndView about() {
        ModelAndView mv = new ModelAndView("web/about");
        return mv;
    }

    /**
     * 跳转的主页
     * 
     * @author hqsunc
     * @return
     * @since 2016年10月17日
     * @see
     */
    @RequestMapping(path = "/desktop")
    public ModelAndView desktop() {
        return new ModelAndView("web/desktop");
    }
}
