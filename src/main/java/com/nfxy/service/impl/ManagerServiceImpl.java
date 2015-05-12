package com.nfxy.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.nfxy.entity.Manager;
import com.nfxy.entity.enums.StatusEnum;
import com.nfxy.exception.AuthenticateFailExcpetion;
import com.nfxy.exception.ReduplicatedNameException;
import com.nfxy.mapper.ManagerMapper;
import com.nfxy.service.ManagerService;
import com.nfxy.util.MD5Utils;

@Service
public class ManagerServiceImpl implements ManagerService {
	
	@Autowired
	private ManagerMapper managerMapper;
	
	public Manager getByNamePwd(String name, String password) 
			throws AuthenticateFailExcpetion{
		Manager manager = managerMapper.getByNamePwd(name, MD5Utils.md5Hex(password));
		if (manager == null) {
			throw new AuthenticateFailExcpetion("用户名或密码错误");
		}
		return manager;
	}

	public Manager getById(String id) {
		return managerMapper.getById(id);
	}
	
	public boolean exist(String name) {
		return managerMapper.exist(name);
	}
	
	public void updatePassword(String name, String oldPwd, String newPwd) 
			throws AuthenticateFailExcpetion {
		if (managerMapper.validate(name, MD5Utils.md5Hex(oldPwd))) {
			managerMapper.updatePassword(name, MD5Utils.md5Hex(newPwd));
		} else {
			throw new AuthenticateFailExcpetion("旧密码录入错误");
		}
	}

	public void createManager(Manager manager) throws ReduplicatedNameException{
		if (exist(manager.getName())) {
			throw new ReduplicatedNameException("用户名已存在");
		}
		manager.setPassword(MD5Utils.md5Hex(manager.getPassword()));
		managerMapper.insert(manager);
		if (CollectionUtils.isNotEmpty(manager.getMenus())) {
			managerMapper.insertManagerMenus(manager);
		}
	}
	
	public void update(Manager manager) {
		managerMapper.deleteManagerMenus(manager);
		if (CollectionUtils.isNotEmpty(manager.getMenus())) {
			managerMapper.insertManagerMenus(manager);
		}
	}
	
	public List<Manager> query(Map<String, Object> criteria, PageBounds page) {
		return managerMapper.query(criteria, page);
	}

	public void enable(String id) {
		managerMapper.updateStatus(id, StatusEnum.ENABLED);
	}

	public void disable(String id) {
		managerMapper.updateStatus(id, StatusEnum.DISABLED);
	}

}
