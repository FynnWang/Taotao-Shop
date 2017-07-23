package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCatListService;

@Service
public class ContentCatListServiceImpl implements ContentCatListService {

    @Autowired
    TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EUTreeNode> getContentCatList(Long parentId) {

        TbContentCategoryExample example = new TbContentCategoryExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> catlist = contentCategoryMapper.selectByExample(example);
        List<EUTreeNode> nodeList = new ArrayList<>();

        for (TbContentCategory tbContentCategory : catlist) {
            EUTreeNode node = new EUTreeNode();
            node.setId(tbContentCategory.getId());
            node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
            node.setText(tbContentCategory.getName());
            nodeList.add(node);
        }
        return nodeList;
    }

    // 添加一个商品管理分类
    @Override
    public TaotaoResult createContentCat(long parentId, String name) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setName(name);
        tbContentCategory.setIsParent(false);
        tbContentCategory.setStatus(1);
        tbContentCategory.setParentId(parentId);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setUpdated(new Date());
        contentCategoryMapper.insert(tbContentCategory);

        TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parent.getIsParent()) {
            parent.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(parent);
        }
        return TaotaoResult.ok(tbContentCategory);
    }

    @Override
    public TaotaoResult deleteContentCat(Long id) {

        Long parentId = contentCategoryMapper.selectByPrimaryKey(id).getParentId();

        contentCategoryMapper.deleteByPrimaryKey(id);

        TbContentCategoryExample example = new TbContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(parentId);

        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        if (list.size() == 0) {
            TbContentCategory category = new TbContentCategory();
            category.setId(parentId);
            category.setIsParent(false);
            contentCategoryMapper.updateByPrimaryKeySelective(category);
        }

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult renameContentCat(Long id, String name) {

        TbContentCategory category = new TbContentCategory();
        category.setId(id);
        category.setName(name);
        contentCategoryMapper.updateByPrimaryKeySelective(category);
        return TaotaoResult.ok();
    }
}
