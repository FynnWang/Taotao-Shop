package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemCatService;
import com.taotao.service.ItemService;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;

	@RequestMapping("/list")
	@ResponseBody
	private List<EUTreeNode> getCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		List<EUTreeNode> list = itemCatService.getCatList(parentId);
		return list;
	}
}
