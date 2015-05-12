package com.nfxy.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.nfxy.entity.Banner;
import com.nfxy.entity.Manager;
import com.nfxy.entity.enums.StatusEnum;
import com.nfxy.mapper.BannerMapper;
import com.nfxy.service.BannerService;

@Service
public class BannerServiceImpl implements BannerService {
	
	@Autowired
	private BannerMapper bannerMapper;
	
	public void insert(Banner banner) {
		bannerMapper.insert(banner);
	}

	public void update(Banner banner) {
		bannerMapper.update(banner);
	}

	public void delete(long id, Manager deleteBy) {
		bannerMapper.updateStatus(id, StatusEnum.DELETED, deleteBy);
	}
	
	public void viewStatistic(long id) {
		bannerMapper.viewStatistic(id);
	}

	public void shareStatistic(long id) {
		bannerMapper.shareStatistic(id);
	}
	
	public Banner getById(long id) {
		return bannerMapper.getById(id);
	}

	public List<Banner> query(Map<String, Object> criteria, PageBounds page) {
		return bannerMapper.query(criteria, page);
	}

	public List<Banner> queryStatistic(Map<String, Object> criteria,
			PageBounds page) {
		return bannerMapper.queryStatistic(criteria, page);
	}

}
