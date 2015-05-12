package com.nfxy.entity;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.nfxy.entity.enums.EntityTypeEnum;
import com.nfxy.entity.enums.StatusEnum;

/**
 * 管理员
 * @author SIYUAN
 */
public class Manager extends Entity<String> {

	private static final long serialVersionUID = -4119427573209795799L;

	@Override
	public EntityTypeEnum getEntityType() {
		return EntityTypeEnum.MANAGER;
	}
	
	private String name;
	
	private String password;
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	private StatusEnum status;
	
	private List<Menu> menus;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
}
