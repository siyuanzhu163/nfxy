package com.nfxy.manager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.nfxy.entity.Manager;
import com.nfxy.entity.enums.StatusEnum;
import com.nfxy.exception.AuthenticateFailExcpetion;
import com.nfxy.exception.ReduplicatedNameException;
import com.nfxy.manager.AJAXResponse;
import com.nfxy.service.ManagerService;
import com.nfxy.service.MenuService;

/**
 * 账户管理
 * @author SIYUAN
 */
@Controller
@RequestMapping("/manager/manager")
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 账户管理
	 * @return
	 */
	@RequestMapping(value = "/self", method = RequestMethod.GET)
	public String self() {
		return "/manager/selfManage";
	}
	
	/**
	 * 账户管理-管理员信息修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/self", method = RequestMethod.POST)
	public AJAXResponse<Object> selfModify(@RequestParam("name") String name, 
			@RequestParam("oldPwd") String oldPwd, 
			@RequestParam("newPwd") String newPwd) {
		AJAXResponse<Object> ajaxResp = new AJAXResponse<Object>();
		try {
			managerService.updatePassword(name, oldPwd, newPwd);
		} catch (AuthenticateFailExcpetion e) {
			ajaxResp.setStatus(AJAXResponse.FAIL);
			ajaxResp.setMsg(e.getMessage());
		}
		return ajaxResp;
	}
	
	/**
	 * 系统用户管理
	 * @return
	 */
	@RequestMapping("")
	public String manage() {
		return "/manager/managerManage";
	}
	
	@ResponseBody
	@RequestMapping("/query")
	public AJAXResponse<Map<String, Object>> query(@RequestParam Map<String, String> params) {
		AJAXResponse<Map<String, Object>> result = new AJAXResponse<Map<String, Object>>();
		
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("keyword", StringUtils.defaultIfBlank(params.get("keyword"), null));
		
		int pageNumber = Integer.parseInt(params.get("pageNumber"));
		int pageSize = Integer.parseInt(params.get("pageSize"));
		PageBounds page = new PageBounds(pageNumber, pageSize, true);
		
		List<Manager> infos = managerService.query(criteria, page);
		
		Map<String, Object> pageInfo = new HashMap<String, Object>();
		pageInfo.put("items", infos);
		pageInfo.put("totalCount", ((PageList<Manager>)infos).getPaginator().getTotalCount());
		result.setContent(pageInfo);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/{id}/enable")
	public AJAXResponse<Object> enable(@PathVariable("id") String id) {
		AJAXResponse<Object> result = new AJAXResponse<Object>();
		
		managerService.enable(id);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/{id}/disable")
	public AJAXResponse<Object> disable(@PathVariable("id") String id) {
		AJAXResponse<Object> result = new AJAXResponse<Object>();
		
		managerService.disable(id);
		
		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String toAdd(ModelMap model) {
		model.put("menus", menuService.getAll());
		return "/manager/managerEdit";
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public AJAXResponse<Object> add(Manager manager) {
		AJAXResponse<Object> result = new AJAXResponse<Object>();
		
		try {
			manager.setId(UUID.randomUUID().toString());
			manager.setStatus(StatusEnum.ENABLED);
			managerService.createManager(manager);
		} catch (ReduplicatedNameException e) {
			result.setStatus(AJAXResponse.FAIL);
			result.setMsg(e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String toEdit(ModelMap model, @PathVariable("id") String id) {
		model.put("menus", menuService.getAll());
		model.put("manager", managerService.getById(id));
		return "/manager/managerEdit";
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public AJAXResponse<Object> edit(Manager manager) {
		AJAXResponse<Object> result = new AJAXResponse<Object>();
		
		managerService.update(manager);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/exist")
	public AJAXResponse<Object> exist(@RequestParam("name") String name) {
		AJAXResponse<Object> result = new AJAXResponse<Object>();
		
		result.setContent(managerService.exist(name));
		
		return result;
	}
	
}
