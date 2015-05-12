package com.nfxy.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;
import com.nfxy.entity.enums.EntityTypeEnum;
import com.nfxy.entity.enums.PartEnum;
import com.nfxy.entity.enums.StatusEnum;

/**
 * 资讯
 * @author SIYUAN
 */
public class Info extends Entity<Long> {

	private static final long serialVersionUID = -8282288831870730612L;
	
	@Override
	public EntityTypeEnum getEntityType() {
		return EntityTypeEnum.INFO;
	}
	
	/**
	 * 资讯类型枚举
	 */
	public static enum TypeEnum {
		NOTICE("通知", new PartEnum[]{PartEnum.NEW, PartEnum.PLAY}),
		ANECDOTE("趣闻", new PartEnum[]{PartEnum.NEW, PartEnum.PLAY}),
		CET4("四级", new PartEnum[]{PartEnum.STUDY}),
		CET6("六级", new PartEnum[]{PartEnum.STUDY}),
		OTHER("其他", new PartEnum[]{PartEnum.NEW, PartEnum.PLAY, PartEnum.STUDY}),
		PART_TIME("兼职", new PartEnum[]{PartEnum.WORK}),
		PRACTICE("实习", new PartEnum[]{PartEnum.WORK}),
		WORK("工作", new PartEnum[]{PartEnum.WORK});
		
		private String desc;
		
		/** 所属板块 */
		private PartEnum[] included;

		private TypeEnum(String desc, PartEnum[] included) {
			this.desc = desc;
			this.included = included;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public PartEnum[] getIncluded() {
			return included;
		}

		public void setIncluded(PartEnum[] included) {
			this.included = included;
		}
		
		private static Map<PartEnum, List<TypeEnum>> groups = 
				new HashMap<PartEnum, List<TypeEnum>>();
		
		static {
			for (TypeEnum type : TypeEnum.values()) {
				for (PartEnum part : type.getIncluded()) {
					List<TypeEnum> types = groups.get(part);
					if (types == null) {
						types = new LinkedList<Info.TypeEnum>();
						groups.put(part, types);
					}
					types.add(type);
				}
			}
		}
		
		/**
		 * 获取该板块下包含的资讯类型
		 * @param part
		 * @return
		 */
		public static List<TypeEnum> get(PartEnum part) {
			return groups.get(part);
		}
		
	}
	
	private PartEnum part;
	
	private TypeEnum type;
	
	private String title;
	
	private String author;
	
	private String cover;
	
	private String summary;
	
	private String content;
	
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

	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
