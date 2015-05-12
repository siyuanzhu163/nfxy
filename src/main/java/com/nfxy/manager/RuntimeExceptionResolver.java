package com.nfxy.manager;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.nfxy.manager.context.RequestContext;

/**
 * 处理应用程序中的RuntimeException
 * @author SIYUAN
 */
@Component
public class RuntimeExceptionResolver implements HandlerExceptionResolver {
	
	private static Log LOGGER = LogFactory.getLog(RuntimeExceptionResolver.class);
	
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView mv = new ModelAndView();
		
		// 日志中记录当然请求的UUID
		String requestUUID = RequestContext.getRequestUUID();
		LOGGER.error("请求[" + requestUUID + "]发生异常：" + ex.getMessage());
		
		// 普通请求时发生异常
		if (((HandlerMethod) handler).getMethodAnnotation(ResponseBody.class) == null) {
			mv.setViewName("/runtimeException");
			mv.addObject("requestUUID", requestUUID);
			mv.addObject("msg", "当前请求发生异常，请将请求编号[" + requestUUID + "]发送给运维人员以便尽快解决问题，谢谢");
		// AJAX请求时发生异常
		} else {
			AJAXResponse<Object> ajaxResp = new AJAXResponse<Object>();
			ajaxResp.setStatus(AJAXResponse.FAIL);
			ajaxResp.setMsg("当前请求发生异常，请将请求编号[" + requestUUID + "]发送给运维人员以便尽快解决问题，谢谢");
			response.setContentType("text/html;charset=utf-8");
			try {
				response.getWriter().write(JSON.toJSONString(ajaxResp));
			} catch (IOException e) {
			}
		}
		return mv;
	}

}
