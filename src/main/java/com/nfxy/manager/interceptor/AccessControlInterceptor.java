package com.nfxy.manager.interceptor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.nfxy.entity.Manager;
import com.nfxy.entity.Menu;
import com.nfxy.manager.AJAXResponse;
import com.nfxy.manager.context.RequestContext;

/**
 * 访问权限验证
 * @author SIYUAN
 */
public class AccessControlInterceptor extends HandlerInterceptorAdapter {
	
	/** 
	 * 无需验证
	 */
	public static final byte NEEDNOT_CHECK = 1;
	
	/**
	 * 验证通过
	 */
	public static final byte PASSED = 2;
	
	/**
	 * 尚未登录
	 */
	public static final byte NOT_LOGIN = -1;
	
	/**
	 * 权限不足
	 */
	public static final byte REFUSED = -2;
	
	/**
	 * 无需验证的请求对应的servlet path
	 */
	public static final Set<String> NEVER_NEED_CHECK = new HashSet<String>();
	
	/**
	 * 登录后无需验证的请求对应的servlet path
	 */
	public static final Set<String> NEEDNOT_CHECK_IF_LOGIN = new HashSet<String>();
	
	static {
		NEVER_NEED_CHECK.addAll(Arrays.asList(new String[]{
				"/manager/login",
				"/manager/logout"
		}));
		NEEDNOT_CHECK_IF_LOGIN.addAll(Arrays.asList(new String[]{
				"/manager/index",
				"/manager/welcome",
				"/manager/picture",
				"/manager/ueditor",
				"/manager/search/info"
		}));
	}
	
	// TO-D0
	// 图片上传
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		byte isAccessable = isAccessable(request.getServletPath());
		if (NEEDNOT_CHECK == isAccessable || PASSED == isAccessable) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		if (handlerMethod.getMethodAnnotation(ResponseBody.class) == null) {
			AJAXResponse<Object> ajaxResp = new AJAXResponse<Object>();
			ajaxResp.setStatus(AJAXResponse.FAIL);
			if (NOT_LOGIN == isAccessable) {
				ajaxResp.setMsg("请先登录再进行相应操作");
			} else {
				ajaxResp.setMsg("权限不足");
			}
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(JSON.toJSONString(ajaxResp));
		} else {
			String redirectPath = (NOT_LOGIN == isAccessable) ? 
					"/manager/login" : "/manager/notPermit.html";
			response.sendRedirect(redirectPath);
		}
		return false;
	}
	
	private byte isAccessable(String servletPath) {
		if (NEVER_NEED_CHECK.contains(servletPath)) {
			return NEEDNOT_CHECK;
		}
		Manager manager = RequestContext.getManager();
		if (manager == null) {
			return NOT_LOGIN;
		}
		for (String path : NEEDNOT_CHECK_IF_LOGIN) {
			if (servletPath.toLowerCase().startsWith(path.toLowerCase())) {
				return NEEDNOT_CHECK;
			}
		}
		for (Menu menu : manager.getMenus()) {
			if (servletPath.toLowerCase().startsWith(menu.getServletPath().toLowerCase())) {
				return PASSED;
			}
		}
		return REFUSED;
	}
	
}
