package com.nfxy.api;

/**
 * API接口返回的entity对象所包含的属性
 * @author SIYUAN
 */
public class APIPropertyConstant {
	
	/* 轮播图 */
	/** 列表 */
	public static String[] BANNER_lIST = new String[] { "id", "title",
			"summary", "picture", "info", "url" , "viewedCount", "sharedCount"};
	
	public static String[] BANNER_lIST_INFO = new String[] { "id"};
	
	/* 资讯 */
	/** 列表 */
	public static String[] INFO_lIST = new String[] { "id", "title", "author",
			"createTime", "cover", "summary", "viewedCount", "sharedCount" };
	
	/** 详情 */
	public static String[] INFO_DETAIL = new String[] { "id", "title", "author",
		"createTime", "viewedCount", "sharedCount" };
	
}
