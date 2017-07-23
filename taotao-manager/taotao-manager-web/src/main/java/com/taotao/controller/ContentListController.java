package com.taotao.controller;

import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.service.ContentListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/4/15.
 */
@Controller
@RequestMapping("/content/query")
public class ContentListController {

    @Autowired
    private ContentListService contentListService;

    @RequestMapping("/list")
    @ResponseBody
    public EUDateGridResult contentList(Integer page, Integer rows, Long categoryId) {

        EUDateGridResult result = contentListService.contentList(page, rows, categoryId);

        return result;

    }


}
