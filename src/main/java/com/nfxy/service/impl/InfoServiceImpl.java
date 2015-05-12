package com.nfxy.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.nfxy.entity.Info;
import com.nfxy.entity.Manager;
import com.nfxy.entity.enums.StatusEnum;
import com.nfxy.mapper.InfoMapper;
import com.nfxy.service.InfoService;

@Service
public class InfoServiceImpl implements InfoService {
	
	@Autowired
	private InfoMapper infoMapper;
	
	public void insert(Info info) {
		infoMapper.insert(info);
	}

	public void update(Info info) {
		infoMapper.update(info);
	}
	
	public void delete(long id, Manager deleteBy) {
		infoMapper.updateStatus(id, StatusEnum.DELETED, deleteBy);
	}
	
	public Info view(long id) {
		infoMapper.viewStatistic(id);
		return infoMapper.getById(id);
	}
	
	public void shareStatistic(long id) {
		infoMapper.shareStatistic(id);
	}
	
	public Info getById(long id) {
		return infoMapper.getById(id);
	}

	public List<Info> query(Map<String, Object> criteria, PageBounds page) {
		return infoMapper.query(criteria, page);
	}

	public List<Info> queryStatistic(Map<String, Object> criteria,
			PageBounds page) {
		return infoMapper.queryStatistic(criteria, page);
	}

}
