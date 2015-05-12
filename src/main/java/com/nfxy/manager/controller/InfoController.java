package com.nfxy.manager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.nfxy.entity.Info;
import com.nfxy.entity.enums.PartEnum;
import com.nfxy.entity.enums.StatusEnum;
import com.nfxy.manager.AJAXResponse;
import com.nfxy.manager.context.RequestContext;
import com.nfxy.service.InfoService;

/**
 * 资讯
 */
@Controller
@RequestMapping("/manager/info")
public class InfoController extends BaseController {
	
	@Autowired
	private InfoService infoService;
	
	@RequestMapping("/{part}")
	public String manage(ModelMap model, @PathVariable("part") String part) {
		model.put("part", StringUtils.upperCase(part));
		return "/info/infoManage";
	}
	
	@ResponseBody
	@RequestMapping("/{part}/query")
	public AJAXResponse<Map<String, Object>> query(@PathVariable("part") String part,
			@RequestParam Map<String, String> params) {
		AJAXResponse<Map<String, Object>> result = new AJAXResponse<Map<String, Object>>();
		
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("part", PartEnum.valueOf(StringUtils.upperCase(part)));
		criteria.put("status", StatusEnum.ENABLED);
		criteria.put("keyword", StringUtils.defaultIfBlank(params.get("keyword"), null));
		
		int pageNumber = Integer.parseInt(params.get("pageNumber"));
		int pageSize = Integer.parseInt(params.get("pageSize"));
		PageBounds page = new PageBounds(pageNumber, pageSize, true);
		
		List<Info> infos = infoService.query(criteria, page);
		
		Map<String, Object> pageInfo = new HashMap<String, Object>();
		pageInfo.put("items", infos);
		pageInfo.put("totalCount", ((PageList<Info>)infos).getPaginator().getTotalCount());
		result.setContent(pageInfo);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{part}/{id}", method = RequestMethod.DELETE)
	public AJAXResponse<Object> delete(@PathVariable("part") String part,
			@PathVariable("id") Long id) {
		AJAXResponse<Object> result = new AJAXResponse<Object>();
		
		infoService.delete(id, RequestContext.getManager());
		
		return result;
	}
	
	@RequestMapping(value = "/{part}/add", method = RequestMethod.GET)
	public String toAdd(ModelMap model, @PathVariable("part") String part) {
		PartEnum partEnum = PartEnum.valueOf(StringUtils.upperCase(part));
		model.put("part", partEnum);
		model.put("types", Info.TypeEnum.get(partEnum));
		return "/info/infoEdit";
	}
	
	@ResponseBody
	@RequestMapping(value = "/{part}/add", method = RequestMethod.POST)
	public AJAXResponse<Object> add(Info info) {
		AJAXResponse<Object> result = new AJAXResponse<Object>();
		
		info.setStatus(StatusEnum.ENABLED);
		info.setCreateBy(RequestContext.getManager());
		info.setUpdateBy(RequestContext.getManager());
		
		infoService.insert(info);
		
		return result;
	}
	
	@RequestMapping(value = "/{part}/{id}", method = RequestMethod.GET)
	public String toEdit(ModelMap model, @PathVariable("part") String part,
			@PathVariable("id") Long id) {
		PartEnum partEnum = PartEnum.valueOf(StringUtils.upperCase(part));
		model.put("part", partEnum);
		model.put("types", Info.TypeEnum.get(partEnum));
		
		model.put("info", infoService.getById(id));
		
		return "/info/infoEdit";
	}
	
	@ResponseBody
	@RequestMapping(value = "/{part}/{id}", method = RequestMethod.POST)
	public AJAXResponse<Object> edit(Info info) {
		AJAXResponse<Object> result = new AJAXResponse<Object>();
		
		info.setStatus(StatusEnum.ENABLED);
		info.setUpdateBy(RequestContext.getManager());
		
		infoService.update(info);
		
		return result;
	}
	
}
