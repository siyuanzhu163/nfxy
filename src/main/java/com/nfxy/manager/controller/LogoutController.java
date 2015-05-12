package com.nfxy.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nfxy.manager.constant.SessionConstant;

@Controller
@RequestMapping("/manager/logout")
public class LogoutController extends BaseController {
	
	/**
	 * 退出
	 * @return
	 */
	@RequestMapping("")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute(SessionConstant.CUR_MANAGER);
		return "/login/login";
	}
	
}
