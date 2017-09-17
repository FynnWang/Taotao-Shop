package com.taotao.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public TaotaoResult contentAdd(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);
        return TaotaoResult.ok();
    }

    @Override
    public EUDateGridResult getContentList(Long categoryId, int page, int rows) {

        PageHelper.startPage(page, rows);
        TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContentList = contentMapper.selectByExample(example);
        System.out.println("isinstance : " + (tbContentList instanceof Page));
        PageInfo<TbContent> infoList = new PageInfo<>(tbContentList);
        EUDateGridResult euDateGridResult = new EUDateGridResult(infoList.getTotal(), tbContentList);
        System.out.println("total:" + infoList.getTotal());
        return euDateGridResult;
    }

}
