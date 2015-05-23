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

import com.alibaba.fastjson.serializer.SerializeFilter;
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
public class APIInfoController extends APIBaseController {
	
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
	public AJAXResponse<List<Info>> query(@PathVariable("part") String part,
			@Param("lastCreateTime") Date lastCreateTime,
			@Param("lastId") Long lastId, 
			@Param("pageSize") Integer pageSize) {
		PartEnum partVal = validatePart(part);
		
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("part", partVal == PartEnum.ALL ? null : partVal);
		criteria.put("status", StatusEnum.ENABLED);
		criteria.put("lastCreateTime", lastCreateTime);
		criteria.put("lastId", lastId);
		PageBounds page = new PageBounds(1, pageSize == null ? 10 : pageSize);
		
		AJAXResponse<List<Info>> result = new AJAXResponse<List<Info>>();
		result.setContent(infoService.query(criteria, page));
		result.setSerializeFilters(new SerializeFilter[]{
				new SimplePropertyPreFilter(Info.class, APIPropertyConstant.INFO_lIST)});
		return result;
	}
	
	/**
	 * 获取资讯详情
	 * @param id 资讯id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/{id}")
	public AJAXResponse<Info> detail(@PathVariable("part") String part,
			@PathVariable("id") long id) {
		validatePart(part);
		
		AJAXResponse<Info> result = new AJAXResponse<Info>();
		result.setContent(infoService.view(id));
		result.setSerializeFilters(new SerializeFilter[]{
				new SimplePropertyPreFilter(Info.class, APIPropertyConstant.INFO_DETAIL)});
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
