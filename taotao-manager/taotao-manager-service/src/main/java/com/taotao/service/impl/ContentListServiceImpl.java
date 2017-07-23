package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/15.
 */
@Service
public class ContentListServiceImpl implements ContentListService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Override
    public EUDateGridResult contentList(int page, int rows, Long categoryId) {

        PageHelper.startPage(page, rows);
        TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        List<TbContent> contentList = tbContentMapper.selectByExample(example);
        EUDateGridResult result = new EUDateGridResult();
        result.setRows(contentList);
        // 取记录总条数
        PageInfo<TbContent> pageInfo = new PageInfo<>(contentList);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
