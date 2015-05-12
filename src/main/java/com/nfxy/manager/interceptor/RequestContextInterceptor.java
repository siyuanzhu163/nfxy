package com.nfxy.manager.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nfxy.entity.Manager;
import com.nfxy.manager.constant.SessionConstant;
import com.nfxy.manager.context.RequestContext;

/**
 * 初始化RequestContext中的值
 * @author SIYUAN
 */
public class RequestContextInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		RequestContext.setRequestUUID(UUID.randomUUID().toString());
		Manager manager = (Manager) request.getSession()
				.getAttribute(SessionConstant.CUR_MANAGER);
		RequestContext.setManager(manager);
		return true;
	}
	
}
