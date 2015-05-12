package com.nfxy.manager.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.nfxy.manager.AccessLog;
import com.nfxy.manager.context.RequestContext;

/**
 * 记录当前请求的相关信息到log日志中，以便后期错误分析、统计
 * @author SIYUAN
 */
public class AccessLogInterceptor extends HandlerInterceptorAdapter {
	
	private static Log LOGGER = LogFactory.getLog(AccessLogInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		@SuppressWarnings("unchecked")
		AccessLog accessLog = new AccessLog(RequestContext.getRequestUUID(),
				RequestContext.getManager(),
				request.getServletPath(),
				request.getMethod(),
				request.getParameterMap());
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(JSON.toJSONString(accessLog));
		}
		return true;
	}
	
}
