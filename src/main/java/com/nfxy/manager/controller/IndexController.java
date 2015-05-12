package com.nfxy.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {

	@RequestMapping("/manager/index")
	public String toLogin() {
		return "/index";
	}
	
	@RequestMapping("/manager/welcome")
	public String welcome() {
		return "/welcome";
	}
	
}
