package com.nfxy.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.nfxy.entity.Banner;
import com.nfxy.entity.Manager;

public interface BannerService {
	
	void insert(Banner banner);
	
	void update(Banner banner);
	
	void delete(long id, Manager deleteBy);
	
	void viewStatistic(long id);
	
	void shareStatistic(long id);
	
	Banner getById(long id);
	
	List<Banner> query(Map<String, Object> criteria, PageBounds page);
	
	/**
	 * 数据统计
	 * @param criteria
	 * @param page
	 * @return
	 */
	List<Banner> queryStatistic(Map<String, Object> criteria, PageBounds page);
	
}
