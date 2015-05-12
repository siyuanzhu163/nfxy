package com.nfxy.entity;

import com.nfxy.entity.enums.EntityTypeEnum;

/**
 * 菜单
 * @author SIYUAN
 */
public class Menu extends Entity<String> {

	private static final long serialVersionUID = -5493081004730107787L;

	@Override
	public EntityTypeEnum getEntityType() {
		return EntityTypeEnum.MENU;
	}

	private String name;
	
	private String servletPath;
	
	private int order;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServletPath() {
		return servletPath;
	}

	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
}
