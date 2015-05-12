package com.nfxy.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.nfxy.entity.Manager;
import com.nfxy.exception.AuthenticateFailExcpetion;
import com.nfxy.exception.ReduplicatedNameException;

public interface ManagerService {
	
	/**
	 * 根据用户名和密码查找有效的管理员
	 * @param name
	 * @param password
	 * @return
	 * @throws AuthenticateFailExcpetion
	 */
	Manager getByNamePwd(String name, String password)
		throws AuthenticateFailExcpetion;
	
	Manager getById(String id);
	
	/**
	 * 判断用户名是否已存在
	 * @param name
	 */
	boolean exist(String name);
	
	/**
	 * 修改密码
	 * @param name
	 * @param oldPwd
	 * @param newPwd
	 * @throws AuthenticateFailExcpetion
	 */
	void updatePassword(String name, String oldPwd, String newPwd) 
			throws AuthenticateFailExcpetion;
	
	/**
	 * 创建管理员
	 * @param manager
	 */
	void createManager(Manager manager) throws ReduplicatedNameException;
	
	void update(Manager manager);
	
	List<Manager> query(Map<String, Object> criteria, PageBounds page);
	
	void enable(String id);
	
	void disable(String id);
	
}
