package com.nfxy.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.nfxy.api.APIPropertyConstant;
import com.nfxy.entity.Info;
import com.nfxy.entity.enums.PartEnum;
import com.nfxy.entity.enums.StatusEnum;
import com.nfxy.manager.AJAXResponse;
import com.nfxy.service.InfoService;

/**
 * APP-资讯接口
 * @author SIYUAN
 */
@Controller("apiInfoController")
@RequestMapping("/api/info/{part}")
public class InfoController extends BaseController {
	
	@Autowired
	private InfoService infoService;
	
	/**
	 * 获取模块下的资讯
	 * @param part 模块
	 * @param lastCreateTime 列表页中最后一条资讯的创建时间
	 * @param lastId 列表页中最后一条资讯的ID
	 * @param pageSize 列表页大小
	 * @return
	 */
	@ResponseBody
	@RequestMapping("")
	public AJAXResponse<String> query(@PathVariable("part") String part,
			@Param("lastCreateTime") Date lastCreateTime,
			@Param("lastId") long lastId, 
			@Param("pageSize") int pageSize) {
		PartEnum partVal = validatePart(part);
		
		AJAXResponse<String> result = new AJAXResponse<String>();
		
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("part", partVal == PartEnum.ALL ? null : partVal);
		criteria.put("status", StatusEnum.ENABLED);
		criteria.put("lastCreateTime", lastCreateTime);
		criteria.put("lastId", lastId);
		
		PageBounds page = new PageBounds(1, pageSize == 0 ? 10 : pageSize);
		List<Info> infos = infoService.query(criteria, page);
		
		String infosJSON = JSON.toJSONString(infos, 
				new SimplePropertyPreFilter(Info.class, APIPropertyConstant.INFO_lIST));
		result.setContent(infosJSON);
		
		return result;
	}
	
	/**
	 * 获取资讯详情
	 * @param id 资讯id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/{id}")
	public AJAXResponse<String> detail(@PathVariable("part") String part,
			@PathVariable("id") long id) {
		validatePart(part);
		
		AJAXResponse<String> result = new AJAXResponse<String>();
		
		Info info = infoService.view(id);
		
		String infoJSON = JSON.toJSONString(info, 
				new SimplePropertyPreFilter(Info.class, APIPropertyConstant.INFO_DETAIL));
		result.setContent(infoJSON);
		
		return result;
	}
	
	/**
	 * 分享资讯
	 * @param id 资讯id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/{id}/share")
	public AJAXResponse<String> share(@PathVariable("part") String part,
			@PathVariable("id") long id) {
		validatePart(part);
		
		AJAXResponse<String> result = new AJAXResponse<String>();
		
		infoService.shareStatistic(id);
		
		return result;
	}
	
	private PartEnum validatePart(String part) {
		try {
			return PartEnum.valueOf(StringUtils.upperCase(part));
		} catch (Exception e) {
			throw new IllegalArgumentException("无效的模块值");
		}
	}
	
}
