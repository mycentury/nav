/**
 * 
 */
package com.nav.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nav.entity.LinkEntity;
import com.nav.service.DaoService;

/**
 * @Desc
 * @author wewenge.yan
 * @Date 2017年1月22日
 * @ClassName IndexController
 */
@Controller
public class IndexController {
	@Autowired
	private DaoService daoService;

	@RequestMapping(value = { "/index" })
	public String index(HttpServletRequest request, ModelMap map) {
		return "direct:/";
	}

	@RequestMapping(value = { "/" })
	public String home(HttpServletRequest request, ModelMap map) {
		List<LinkEntity> links = daoService.query(null, LinkEntity.class);
		map.put("links", links);
		return "index";
	}
}
