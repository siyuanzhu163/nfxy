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
import com.nfxy.entity.Banner;
import com.nfxy.entity.enums.PartEnum;
import com.nfxy.entity.enums.StatusEnum;
import com.nfxy.manager.AJAXResponse;
import com.nfxy.manager.context.RequestContext;
import com.nfxy.service.BannerService;

/**
 * 轮播图
 */
@Controller
@RequestMapping("/manager/banner")
public class BannerController extends BaseController {
	
	@Autowired
	private BannerService bannerService;
	
	@RequestMapping("")
	public String manage(ModelMap model) {
		model.put("parts", PartEnum.values());
		return "/banner/bannerManage";
	}
	
	@ResponseBody
	@RequestMapping("/query")
	public AJAXResponse<Map<String, Object>> query(@RequestParam Map<String, String> params) {
		AJAXResponse<Map<String, Object>> result = new AJAXResponse<Map<String, Object>>();
		
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("status", StatusEnum.ENABLED);
		criteria.put("keyword", StringUtils.defaultIfBlank(params.get("keyword"), null));
		
		int pageNumber = Integer.parseInt(params.get("pageNumber"));
		int pageSize = Integer.parseInt(params.get("pageSize"));
		PageBounds page = new PageBounds(pageNumber, pageSize, true);
		
		List<Banner> banners = bannerService.query(criteria, page);
		
		Map<String, Object> pageInfo = new HashMap<String, Object>();
		pageInfo.put("items", banners);
		pageInfo.put("totalCount", ((PageList<Banner>)banners).getPaginator().getTotalCount());
		result.setContent(pageInfo);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public AJAXResponse<Object> delete(@PathVariable("id") Long id) {
		AJAXResponse<Object> result = new AJAXResponse<Object>();
		
		bannerService.delete(id, RequestContext.getManager());
		
		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String toAdd(ModelMap model) {
		model.put("parts", PartEnum.values());
		return "/banner/bannerEdit";
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public AJAXResponse<Object> add(Banner banner) {
		AJAXResponse<Object> result = new AJAXResponse<Object>();
		
		banner.setStatus(StatusEnum.ENABLED);
		banner.setCreateBy(RequestContext.getManager());
		banner.setUpdateBy(RequestContext.getManager());
		
		bannerService.insert(banner);
		
		return result;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String toEdit(ModelMap model, @PathVariable("id") Long id) {
		model.put("parts", PartEnum.values());
		
		model.put("banner", bannerService.getById(id));
		
		return "/banner/bannerEdit";
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public AJAXResponse<Object> edit(Banner banner) {
		AJAXResponse<Object> result = new AJAXResponse<Object>();
		
		banner.setStatus(StatusEnum.ENABLED);
		banner.setUpdateBy(RequestContext.getManager());
		
		bannerService.update(banner);
		
		return result;
	}
	
}
