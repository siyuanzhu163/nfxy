package com.nfxy.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.nfxy.entity.enums.EntityTypeEnum;
import com.nfxy.entity.enums.PartEnum;
import com.nfxy.entity.enums.StatusEnum;

/**
 * 轮播图
 * @author SIYUAN
 */
public class Banner extends Entity<Long> {

	private static final long serialVersionUID = 6213741272185303844L;

	@Override
	public EntityTypeEnum getEntityType() {
		return EntityTypeEnum.BANNER;
	}
	
	private PartEnum part;
	
	private String title;
	
	private String picture;
	
	private String summary;
	
	private Info info;
	
	private String url;
	
	private int weight;
	
	private StatusEnum status;
	
	private int viewedCount;
	
	private int sharedCount;
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	private Manager createBy;
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	private Manager updateBy;

	public PartEnum getPart() {
		return part;
	}

	public void setPart(PartEnum part) {
		this.part = part;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public int getViewedCount() {
		return viewedCount;
	}

	public void setViewedCount(int viewedCount) {
		this.viewedCount = viewedCount;
	}

	public int getSharedCount() {
		return sharedCount;
	}

	public void setSharedCount(int sharedCount) {
		this.sharedCount = sharedCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Manager getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Manager createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Manager getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Manager updateBy) {
		this.updateBy = updateBy;
	}

}
