package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCatListService;

@Controller
@RequestMapping("/content/category")
public class ContentCatListController {

    @Autowired
    private ContentCatListService contenCatListService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {

        List<EUTreeNode> nodeList = contenCatListService.getContentCatList(parentId);

        return nodeList;
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createContentCat(Long parentId, String name) {

        TaotaoResult result = contenCatListService.createContentCat(parentId, name);

        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContentCat(Long id) {

//		System.out.println(parentId + ":" + id);

        TaotaoResult result = contenCatListService.deleteContentCat(id);

        return result;
    }


    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult renameContextCat(Long id, String name) {

        return contenCatListService.renameContentCat(id, name);
    }

}
