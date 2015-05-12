package com.nfxy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.nfxy.entity.Manager;
import com.nfxy.entity.enums.StatusEnum;

public interface ManagerMapper {
	
	/**
	 * 根据用户名和密码查找有效的管理员
	 * @param name
	 * @param password
	 * @return
	 */
	Manager getByNamePwd(@Param("name") String name, @Param("password") String password);
	
	Manager getById(String id);
	
	/**
	 * 判断用户名是否已存在
	 * @param name
	 */
	boolean exist(String name);
	
	/**
	 * 判断用户名和密码是否正确
	 * @param name
	 * @param password
	 */
	boolean validate(@Param("name") String name, @Param("password") String password);
	
	/**
	 * 修改密码
	 * @param name
	 * @param password
	 */
	void updatePassword(@Param("name") String name, @Param("password") String password);
	
	/**
	 * 创建管理员
	 * @param manager
	 */
	void insert(Manager manager);
	
	/**
	 * 分配管理员权限
	 * @param manager
	 */
	void insertManagerMenus(Manager manager);
	
	/**
	 * 清空管理员权限
	 * @param manager
	 */
	void deleteManagerMenus(Manager manager);
	
	List<Manager> query(Map<String, Object> criteria, PageBounds page);
	
	void updateStatus(@Param("id") String id, @Param("status") StatusEnum status);
	
}
