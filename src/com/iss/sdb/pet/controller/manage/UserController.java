/**
 * UserController.java 2016年12月22日
 * 
 * Copyright 2001-2016 All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.iss.sdb.pet.controller.manage;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.iss.sdb.pet.models.UserModel;
import com.iss.sdb.pet.pojo.AjaxResponse;
import com.iss.sdb.pet.pojo.Page;
import com.iss.sdb.pet.service.UserService;
import com.iss.sdb.pet.commons.Constants;

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
@SessionAttributes(Constants.Commons.SESSION_STORE_USER)
@RequestMapping("m/user")
public class UserController {
    /**
     * 日志系统
     */
    private Log logger = LogFactory.getLog(UserController.class);

    /**
     * 用户Service
     */
    @Autowired
    private UserService userService;

    /**
     * 用户列表页
     * <p>
     * GET / user / list
     *
     * @author hqsunc
     * @return
     * @since 2016年11月18日
     */
    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public ModelAndView list() {

        logger.debug("UserController list begin");

        ModelAndView mv = new ModelAndView("mng/user/user_list");
        return mv;
    }

    /**
     * 用户列表页
     * <p>
     * GET / user / list
     *
     * @author hqsunc
     * @return
     * @since 2016年11月18日
     */
    @RequestMapping(method = RequestMethod.GET, path = "/stafflist")
    public ModelAndView staffList() {

        logger.debug("UserController staffList begin");

        ModelAndView mv = new ModelAndView("mng/user/staff_list");
        return mv;
    }

    /**
     * 跳转用户新增页
     * <p>
     * GET / user / add
     *
     * @author hqsunc
     * @return
     * @since 2016年11月18日
     */
    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public ModelAndView add() {
        logger.debug("UserController add begin");

        ModelAndView mv = new ModelAndView("mng/user/user_edit");
        mv.addObject("user", new UserModel());
        return mv;
    }

    /**
     * 根据用户ID查询单条数据
     * <p>
     * GET /user /get
     *
     * @author hqsunc
     * @param id
     *            用户ID
     * @return 用户信息
     * @since 2016年11月19日
     */
    @RequestMapping(method = RequestMethod.GET, path = "/get/{id:\\d+}")
    public ModelAndView get(@PathVariable(value = "id") Long id) {
        logger.debug("UserController get begin");

        UserModel user = userService.get(id);
        ModelAndView mv = new ModelAndView("mng/user/user_details");
        mv.addObject("user", user);
        logger.debug("UserController get end");

        return mv;
    }

    /**
     * 方法描述：跳转用户编辑页
     * <p>
     * GET / user / edit
     *
     * @author hqsunc
     * @param id
     * @return
     * @since 2016年11月18日
     */

    @RequestMapping(method = RequestMethod.GET, path = "/edit/{id:\\d+}")
    public ModelAndView edit(@PathVariable(value = "id") Long id) {

        logger.debug("UserController edit begin");

        UserModel user = userService.get(id);
        ModelAndView mv = new ModelAndView("mng/user/user_edit");
        mv.addObject("user", user);

        return null != user ? mv : this.list();
    }

    /**
     * 方法描述：添加用户信息
     * <p>
     * POST / user / create
     *
     * @author hqsunc
     * @param user
     *            用户信息模型
     * @param userSession.
     *            用户会话数据
     * @return 持久化操作码
     * @since 2016年11月18日
     */
    @RequestMapping(method = RequestMethod.POST, path = "/create")
    @ResponseBody
    public String add(UserModel user) {
        logger.debug("UserController add begin" + user);

        String aj;
        int result = userService.add(user);
        if (result > 0) {
            aj = AjaxResponse.fromCode(result).toJSONString();
        }
        else {
            aj = AjaxResponse.fromCode(result).toJSONString();
        }

        logger.debug("UserController add end " + aj);

        return aj;

    }

    /**
     * 方法描述：删除用户信息
     * <p>
     * POST / user / delete
     *
     * @author hqsunc
     * @param user
     *            用户模型
     * @param userSession
     *            用户会话模型
     * @return 持久化操作码
     * @since 2016年11月18日
     */
    @RequestMapping(method = RequestMethod.POST, path = "/delete")
    public String delete(@RequestBody UserModel user) {

        logger.debug("UserController delete begin" + user);

        String aj;

        int result = userService.delete(user);
        if (result > 0) {
            aj = AjaxResponse.fromCode(result).toJSONString();
        }
        else {
            aj = AjaxResponse.fromCode(result).toJSONString();
        }
        logger.debug("UserController delete end" + aj);

        return aj;

    }

    /**
     * 方法描述：更新用户信息
     * <p>
     * POST / user/update
     *
     * @author hqsunc
     * @param user
     * @param userSession
     * @return
     * @since 2016年11月18日
     * @see
     */
    @RequestMapping(method = RequestMethod.POST, path = "/update")
    @ResponseBody
    public String update(UserModel user) {
        logger.debug("UserController update begin" + user);

        String aj;
        int result = userService.update(user);
        if (result > 0) {
            aj = AjaxResponse.fromCode(result).toJSONString();
        }
        else {
            aj = AjaxResponse.fromCode(result).toJSONString();
        }

        logger.debug("UserController update end" + aj);

        return aj;
    }

    /**
     * 方法描述：用户信息分页查询
     * <p>
     * POST / user/findPage
     *
     * @author hqsunc
     * @param user
     *            用户信息模型
     * @param page
     *            分页模型
     * @return 用户信息列表分袂
     * @since 2016年11月18日
     * @see
     */
    @RequestMapping(method = RequestMethod.POST, path = "/findPage")
    @ResponseBody
    public String findPage(UserModel user, Page page) {
        logger.debug("UserController findPage begin" + page);
        page.setSearchParam(user);
        userService.findPage(page);
        String aj = AjaxResponse.fromData(page).toJSONString();
        logger.debug("UserController findPage end" + aj);
        return aj;

    }

    /**
     * 
     * <summary>
     * <description>
     *
     * @author hqsunc
     * @param user
     * @return
     * @since 2017年3月23日
     * @see
     */
    @RequestMapping(method = RequestMethod.POST, path = "/findList")
    @ResponseBody
    public String findList(UserModel user) {
        logger.debug("UserController findList begin" + user);
        List<UserModel> list = userService.findList(user);
        String aj = AjaxResponse.fromData(list).toJSONString();
        logger.debug("UserController findList end" + aj);
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
        logger.debug("UserController checkNameEml begin:" + user.toString());
        UserModel result = userService.checkAccount(user);
        String aj;
        logger.debug("UserController checkNameEml end");
        if (result != null) {//账号已经存在
            aj = AjaxResponse.success().toJSONString();
        }
        else {
            aj = AjaxResponse.error().toJSONString();
        }
        return aj;
    }
}
