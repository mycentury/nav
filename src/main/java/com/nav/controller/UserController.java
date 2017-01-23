/**
 * 
 */
package com.nav.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nav.constant.ResultType;
import com.nav.constant.RoleType;
import com.nav.entity.Result;
import com.nav.entity.UserEntity;
import com.nav.service.DaoService;

/**
 * @Desc
 * @author wewenge.yan
 * @Date 2017年1月22日
 * @ClassName IndexController
 */
@Controller
@RequestMapping(value = { "/user" })
public class UserController {

	@Autowired
	private DaoService daoService;

	@RequestMapping(value = { "/to_login" })
	public String toLogin(HttpServletRequest request, ModelMap map) {
		return "user/login";
	}

	@RequestMapping(value = { "/login" })
	public String login(HttpServletRequest request, ModelMap map) {
		return "user/login";
	}

	@RequestMapping(value = { "/to_register" })
	public String toRegister(HttpServletRequest request, ModelMap map) {
		return "user/register";
	}

	@RequestMapping(value = { "/register" })
	public String register(HttpServletRequest request, ModelMap map) {
		return "user/register";
	}

	@RequestMapping(value = "logout")
	public String logout(HttpServletRequest request) {
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
