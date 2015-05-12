package com.nfxy.manager;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * AJAX响应格式
 * @author SIYUAN
 *
 */
public class AJAXResponse<T> {
	
	public static final int SUCCESS = 200;
	
	public static final int FAIL = 500;
	
	@JSONField(ordinal=0)
	private int status = SUCCESS;
	
	@JSONField(ordinal=1)
	private String msg;
	
	@JSONField(ordinal=2)
	private T content;
	
	public AJAXResponse() {
		
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}
	
}
