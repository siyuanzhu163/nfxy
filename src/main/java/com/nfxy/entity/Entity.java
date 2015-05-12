package com.nfxy.entity;

import java.io.Serializable;

import com.nfxy.entity.enums.EntityTypeEnum;

@SuppressWarnings("serial")
public abstract class Entity<T> implements Serializable {
	
	private T id;

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}
	
	public abstract EntityTypeEnum getEntityType();
	
}
