/**
 * 
 */
package com.nav.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nav.constant.ResultType;
import com.nav.constant.RoleType;
import com.nav.entity.Result;
import com.nav.entity.UserEntity;
import com.nav.service.DaoService;
import com.nav.service.DaoService.Condition;
import com.nav.service.SessionService;

/**
 * @Desc
 * @author wewenge.yan
 * @Date 2017年1月22日
 * @ClassName IndexController
 */
@Controller
@RequestMapping(value = { "/user" })
public class UserController {
	private final static Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private SessionService sessionService;
	@Autowired
	private DaoService daoService;

	@RequestMapping(value = { "/to_login" })
	public String toLogin(HttpServletRequest request, ModelMap map) {
		if (sessionService.hasLogin(request)) {
			return "redirect:/";
		}
		String errorMsg = request.getParameter("errorMsg");
		if (!StringUtils.isEmpty(errorMsg)) {
			map.put("errorMsg", errorMsg);
		}
		return "user/login";
	}

	@RequestMapping(value = { "/login" })
	public String login(HttpServletRequest request, UserEntity user, String checkcode, RedirectAttributes attr) {
		attr.addFlashAttribute("user", user);
		if (!checkcode.equalsIgnoreCase(sessionService.getCheckcode(request))) {
			attr.addFlashAttribute("errorMsg", "验证码错误！");
			return "redirect:/user/login";
		}
		user.setUsertype(RoleType.USER.getCode());
		Condition condition = new Condition();
		condition.addParam("usertype", "=", RoleType.USER.getCode());
		condition.addParam("username", "=", user.getUsername());
		condition.addParam("password", "=", user.getPassword());
		boolean exists = daoService.exists(condition, UserEntity.class);
		if (!exists) {
			attr.addFlashAttribute("errorMsg", "username or password error");
			return "redirect:/user/login";
		}

		sessionService.setUsername(request, user.getUsername());
		sessionService.setUsertype(request, user.getUsertype());
		return "redirect:/";
	}

	@RequestMapping(value = { "/to_register" })
	public String toRegister(HttpServletRequest request, ModelMap map) {
		return "user/register";
	}

	@RequestMapping(value = { "/register" })
	public String register(HttpServletRequest request, UserEntity user, RedirectAttributes attr) {
		attr.addFlashAttribute("user", user);
		user.setUsertype(RoleType.USER.getCode());
		String id = user.getUsertype() + "-" + user.getUsername();
		boolean exists = daoService.existsById(id, UserEntity.class);
		if (exists) {
			attr.addFlashAttribute("errorMsg", "username allready exists");
			return "redirect:/user/register";
		}
		user.setPrimaryKey(user.getUsertype(), user.getUsername());
		daoService.insert(user);
		sessionService.setUsername(request, user.getUsername());
		sessionService.setUsertype(request, user.getUsertype());
		return "redirect:/";
	}

	@RequestMapping(value = "logout")
	public String logout(HttpServletRequest request) {
		sessionService.removeUsername(request);
		sessionService.removeUsertype(request);
		return "redirect:/user/login";
	}

	@RequestMapping("check_username")
	public @ResponseBody Result<Boolean> checkUsername(HttpServletRequest request, String username, ModelMap map) {
		Result<Boolean> result = new Result<Boolean>();
		String id = RoleType.USER.getCode() + "-" + username;
		boolean exists = daoService.existsById(id, UserEntity.class);
		if (exists) {
			result.setResultStatusAndMsg(ResultType.USER_EXISTS, null);
			result.setData(false);
			return result;
		}
		result.setResultStatusAndMsg(ResultType.SUCCESS, null);
		result.setData(false);
		return result;
	}
}
