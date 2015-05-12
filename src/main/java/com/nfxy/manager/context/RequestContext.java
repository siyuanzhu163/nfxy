package com.nfxy.manager.context;

import com.nfxy.entity.Manager;

/**
 * 在线程上下文中记录当前请求中的一些信息
 * @author SIYUAN
 */
public class RequestContext {
	
	private static ThreadLocal<String> requestUUID = new ThreadLocal<String>();
	
	private static ThreadLocal<Manager> requestManager = new ThreadLocal<Manager>();
	
	public static String getRequestUUID() {
		return requestUUID.get();
	}

	public static void setRequestUUID(String uuid) {
		requestUUID.set(uuid);
	}

	public static Manager getManager() {
		return requestManager.get();
	}

	public static void setManager(Manager manager) {
		requestManager.set(manager);
	}
	
}
