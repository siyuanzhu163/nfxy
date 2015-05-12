package com.nfxy.mybatis;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.ibatis.logging.LogFactory;

/**
 * 设置MyBatis使用的日志系统
 * 必须放在调用所有其他MyBatis方法前
 */
public class LogSetListener implements ServletContextListener {
	
	public static final String PARAM_NAME = "mybatisLog";
	
	public static final String LOG_SLF4J = "slf4j";
	
	public static final String LOG_LOG4J = "log4j";
	
	public static final String LOG_JDK = "jdk";
	
	public static final String LOG_COMMONS = "commons-logging";
	
	public static final String LOG_STDOUT = "stdout";
	
	public void contextInitialized(ServletContextEvent sce) {
		String mybatisLog = sce.getServletContext().getInitParameter(PARAM_NAME);
		if (mybatisLog == null) {
			return;
		}
		if (LOG_SLF4J.equals(mybatisLog)) {
			LogFactory.useSlf4jLogging();
		} else if (LOG_LOG4J.equals(mybatisLog)) {
			LogFactory.useLog4JLogging();
		} else if (LOG_JDK.equals(mybatisLog)) {
			LogFactory.useJdkLogging();
		} else if (LOG_COMMONS.equals(mybatisLog)) {
			LogFactory.useCommonsLogging();
		} else if (LOG_STDOUT.equals(mybatisLog)) {
			LogFactory.useStdOutLogging();
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}

}
