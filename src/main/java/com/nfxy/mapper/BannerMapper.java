package com.nfxy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.nfxy.entity.Banner;
import com.nfxy.entity.Manager;
import com.nfxy.entity.enums.StatusEnum;

public interface BannerMapper {
	
	void insert(Banner banner);
	
	void update(Banner banner);
	
	void updateStatus(@Param("id") long id, @Param("status") StatusEnum status,
			@Param("updateBy") Manager updateBy);
	
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
