package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

/**
 * 
 * @author Administrator
 *
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	// {itemId}指从url接收一个参数，如果与下面的方法中的itemId的参数相同，则不需要value
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		System.out.println(11111);
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}

	@RequestMapping("/item/list")
	@ResponseBody
	public EUDateGridResult getItemList(Integer page, Integer rows) {
		EUDateGridResult result = itemService.getItemList(page, rows);
		// 返回的对象属性符合此格式要求{total:”2”,rows:[{“id”:”1”,”name”,”张三”},{“id”:”2”,”name”,”李四”}]}
		return result;
	}

	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	private TaotaoResult createItem(TbItem item, String desc) throws Exception {
		TaotaoResult result = itemService.createItem(item, desc);
		return result;
	}

}
