package com.nfxy.manager.controller.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.nfxy.entity.Info;
import com.nfxy.entity.enums.StatusEnum;
import com.nfxy.manager.AJAXResponse;
import com.nfxy.service.InfoService;

/**
 * 资讯查询
 */
@Controller
@RequestMapping("/manager/search/info")
public class InfoSearchController {
	
	@Autowired
	private InfoService infoService;
	
	@ResponseBody
	@RequestMapping("")
	public AJAXResponse<List<Info>> search(@RequestParam Map<String, String> params) {
		AJAXResponse<List<Info>> result = new AJAXResponse<List<Info>>();
		
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("status", StatusEnum.ENABLED);
		criteria.put("keyword", StringUtils.defaultIfBlank(params.get("keyword"), null));
		
		int pageNumber = Integer.parseInt(params.get("pageNumber"));
		int pageSize = Integer.parseInt(params.get("pageSize"));
		PageBounds page = new PageBounds(pageNumber, pageSize, false);
		
		result.setContent(infoService.query(criteria, page));
		
		return result;
	}
	
}
