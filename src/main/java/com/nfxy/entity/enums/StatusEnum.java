package com.nfxy.entity.enums;

/**
 * 状态
 * @author SIYUAN
 */
public enum StatusEnum {
	
	ENABLED("有效/上架"),
	DISABLED("无效/下架"),
	DELETED("删除");
	
	private String desc;
	
	private StatusEnum(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
