package com.taotao.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.JsonUtils;
import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;
import com.taotao.rest.service.impl.ItemCatServiceImpl;

@Controller
public class ItemCatController {
	
	private static final Logger logger = LoggerFactory.getLogger(ItemCatController.class);
	
	@Autowired
	ItemCatService itemCatService;

	@RequestMapping(value = "/itemcat/list",produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		
		logger.info("Start to exercute Controller");
		ItemCatResult itemCatResult = itemCatService.getItemCatResult();
		String json = JsonUtils.objectToJson(itemCatResult);
		String result = callback + "(" + json + ");";
		
		logger.info("Everything is over");
		return result;
	}
	
	
	/*@RequestMapping("/itemcat/list")
	@ResponseBody
	public Object getItemCatList(String callback) {
		ItemCatResult itemCatResult = itemCatService.getItemCatResult();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(itemCatResult);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}*/
	

}
