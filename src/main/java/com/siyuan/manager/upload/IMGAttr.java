package com.siyuan.manager.upload;

import java.io.Serializable;

/**
 * 图片属性
 * @author SIYUAN
 *
 */
public class IMGAttr implements Serializable{
	
	private static final long serialVersionUID = -3143508126171727189L;

	private int width;
	
	private int height;
	
	public IMGAttr(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
