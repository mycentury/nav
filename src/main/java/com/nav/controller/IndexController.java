/**
 * 
 */
package com.nav.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Desc
 * @author wewenge.yan
 * @Date 2017年1月22日
 * @ClassName IndexController
 */
@Controller
public class IndexController {

	@RequestMapping(value = { "/index" })
	public String index(HttpServletRequest request, ModelMap map) {
		return "direct:/";
	}

	@RequestMapping(value = { "/" })
	public String home(HttpServletRequest request, ModelMap map) {
		return "index";
	}
}
