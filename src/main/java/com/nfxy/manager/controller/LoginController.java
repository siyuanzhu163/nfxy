package com.nfxy.manager.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nfxy.entity.Manager;
import com.nfxy.exception.AuthenticateFailExcpetion;
import com.nfxy.manager.AJAXResponse;
import com.nfxy.manager.constant.SessionConstant;
import com.nfxy.service.ManagerService;

@Controller
@RequestMapping("/manager/login")
public class LoginController extends BaseController {
	
	@Autowired
	private ManagerService managerService;
	
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String toLogin() {
		return "/login/login";
	}
	
	/**
	 * 登录
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public AJAXResponse<Object> login(HttpServletRequest request, 
			HttpServletResponse response, 
			@RequestParam("name") String name, 
			@RequestParam("password") String password,
			@RequestParam(value = "saveName", defaultValue = "false") boolean saveName) {
		AJAXResponse<Object> ajaxResp = new AJAXResponse<Object>();
		try {
			Manager manager = managerService.getByNamePwd(name, password);
			request.getSession().setAttribute(SessionConstant.CUR_MANAGER, manager);
			if (saveName) {
				response.addCookie(new Cookie("name", name));
			}
		} catch (AuthenticateFailExcpetion e) {
			ajaxResp.setStatus(AJAXResponse.FAIL);
			ajaxResp.setMsg(e.getMessage());
		}
		return ajaxResp;
	}
	
}
