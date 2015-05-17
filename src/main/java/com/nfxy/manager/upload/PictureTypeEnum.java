package com.nfxy.manager.upload;

/**
 * 图片类型
 * @author SIYUAN
 */
public enum PictureTypeEnum {
	
	INFO_CONTENT("资讯正文", null, "/info/content"),
	INFO_COVER("资讯封面", null, "/info/cover"),
	BANNER("轮播图", null, "/banner");
	
	/**
	 * 类型描述
	 */
	private String desc;
	
	/**
	 * 该类型图片的规格，null表示无限制
	 */
	private IMGAttr imgAttr;
	
	/**
	 * 在图片服务器中的路径
	 */
	private String path;
	
	private PictureTypeEnum(String desc, IMGAttr imgAttr, String path) {
		this.desc = desc;
		this.imgAttr = imgAttr;
		this.path = path;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public IMGAttr getImgAttr() {
		return imgAttr;
	}

	public void setImgAttr(IMGAttr imgAttr) {
		this.imgAttr = imgAttr;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
