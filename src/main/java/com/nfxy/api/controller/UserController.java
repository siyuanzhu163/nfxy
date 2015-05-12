package com.nfxy.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nfxy.manager.AJAXResponse;

/**
 * APP-用户接口
 * @author SIYUAN
 */
@Controller
@RequestMapping("/api/user")
public class UserController {
	
	/**
	 * 注册
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/register")
	public AJAXResponse<String> register() {
		AJAXResponse<String> result = new AJAXResponse<String>();
		// 记录用户信息
		return result;
	}
	
}
