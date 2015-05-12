package com.nfxy.manager.controller.statistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.nfxy.entity.Banner;
import com.nfxy.entity.Info;
import com.nfxy.entity.enums.PartEnum;
import com.nfxy.manager.AJAXResponse;
import com.nfxy.manager.controller.BaseController;
import com.nfxy.service.BannerService;
import com.nfxy.service.InfoService;

/**
 * 数据统计-资讯和轮播图
 * @author SIYUAN
 */
@Controller
@RequestMapping("/manager/statistic/infobanner")
public class InfoBannerStatisticController extends BaseController {
	
	@Autowired
	private InfoService infoService;
	
	@Autowired
	private BannerService bannerService;
	
	@RequestMapping("")
	public String index(ModelMap map) {
		map.put("parts", PartEnum.values());
		return "/statistic/infobannerStatistic";
	}
	
	@ResponseBody
	@RequestMapping("/info/{part}")
	public AJAXResponse<Map<String, Object>> info(@PathVariable("part") String part,
			@RequestParam Map<String, String> params) {
		AJAXResponse<Map<String, Object>> result = new AJAXResponse<Map<String, Object>>();
		
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("part", PartEnum.valueOf(StringUtils.upperCase(part)));
		
		int pageNumber = Integer.parseInt(params.get("pageNumber"));
		int pageSize = Integer.parseInt(params.get("pageSize"));
		PageBounds page = new PageBounds(pageNumber, pageSize, true);
		List<Info> infos = infoService.queryStatistic(criteria, page);
		
		Map<String, Object> pageInfo = new HashMap<String, Object>();
		pageInfo.put("items", infos);
		pageInfo.put("totalCount", ((PageList<Info>)infos).getPaginator().getTotalCount());
		result.setContent(pageInfo);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/banner/{part}")
	public AJAXResponse<Map<String, Object>> banner(@PathVariable("part") String part,
			@RequestParam Map<String, String> params) {
		AJAXResponse<Map<String, Object>> result = new AJAXResponse<Map<String, Object>>();
		
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("part", PartEnum.valueOf(StringUtils.upperCase(part)));
		
		int pageNumber = Integer.parseInt(params.get("pageNumber"));
		int pageSize = Integer.parseInt(params.get("pageSize"));
		PageBounds page = new PageBounds(pageNumber, pageSize, true);
		List<Banner> banners = bannerService.queryStatistic(criteria, page);
		
		Map<String, Object> pageInfo = new HashMap<String, Object>();
		pageInfo.put("items", banners);
		pageInfo.put("totalCount", ((PageList<Banner>)banners).getPaginator().getTotalCount());
		result.setContent(pageInfo);
		return result;
	}
	
}
