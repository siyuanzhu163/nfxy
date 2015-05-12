package com.nfxy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.nfxy.entity.Info;
import com.nfxy.entity.Manager;
import com.nfxy.entity.enums.StatusEnum;

public interface InfoMapper {
	
	void insert(Info info);
	
	void update(Info info);
	
	void updateStatus(@Param("id") long id, @Param("status") StatusEnum status,
			@Param("updateBy") Manager updateBy);
	
	void viewStatistic(long id);
	
	void shareStatistic(long id);
	
	Info getById(long id);
	
	List<Info> query(Map<String, Object> criteria, PageBounds page);
	
	/**
	 * 数据统计
	 * @param criteria
	 * @param page
	 * @return
	 */
	List<Info> queryStatistic(Map<String, Object> criteria, PageBounds page);
	
}
