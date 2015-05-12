package com.nfxy.entity.enums;

/**
 * 实体
 * @author SIYUAN
 */
public enum EntityTypeEnum {
	
	MENU("com.nfxy.entity.Menu"),
	MANAGER("com.nfxy.entity.Manager"),
	INFO("com.nfxy.entity.Info"),
	BANNER("com.nfxy.entity.Banner");;
	
	private String className;
	
	private EntityTypeEnum(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
}
