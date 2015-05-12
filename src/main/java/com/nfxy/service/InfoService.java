package com.nfxy.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.nfxy.entity.Info;
import com.nfxy.entity.Manager;

public interface InfoService {
	
	void insert(Info info);
	
	void update(Info info);
	
	void delete(long id, Manager deleteBy);
	
	/**
	 * 浏览，阅读量+1
	 * @param id
	 * @return
	 */
	Info view(long id);
	
	Info getById(long id);
	
	/**
	 * 分享量+1
	 * @param id
	 * @return
	 */
	void shareStatistic(long id);
	
	List<Info> query(Map<String, Object> criteria, PageBounds page);
	
	/**
	 * 数据统计
	 * @param criteria
	 * @param page
	 * @return
	 */
	List<Info> queryStatistic(Map<String, Object> criteria, PageBounds page);
	
}
