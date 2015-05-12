package com.nfxy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nfxy.entity.Menu;
import com.nfxy.mapper.MenuMapper;
import com.nfxy.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuMapper menuMapper;
	
	public List<Menu> getAll() {
		return menuMapper.getAll();
	}

}
