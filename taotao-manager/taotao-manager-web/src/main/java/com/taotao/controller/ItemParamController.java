package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;

	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public TaotaoResult getItemParamByCId(@PathVariable Long itemCatId) {

		System.out.println("good");

		itemParamService.getItemCatByCId(itemCatId);

		return itemParamService.getItemCatByCId(itemCatId);
	}

	@RequestMapping("/save/{itemCatId}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable Long itemCatId, String paramData) {

		TbItemParam itemParam = new TbItemParam();
		itemParam.setParamData(paramData);
		itemParam.setItemCatId(itemCatId);

		return itemParamService.insertItemParam(itemParam);
	}

}
