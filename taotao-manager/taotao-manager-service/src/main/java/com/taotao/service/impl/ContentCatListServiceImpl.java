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
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EUTreeNode> getContentCatList(Long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> resultList = contentCategoryMapper.selectByExample(example);
        List<EUTreeNode> euTreeNodesList = new ArrayList<>();
        for (TbContentCategory tbContentCategory : resultList) {
            EUTreeNode euTreeNode = new EUTreeNode();
            euTreeNode.setId(tbContentCategory.getId());
            euTreeNode.setText(tbContentCategory.getName());
            euTreeNode.setState(tbContentCategory.getIsParent() ? "closed" : "open");
            euTreeNodesList.add(euTreeNode);
        }
        return euTreeNodesList;
    }


    //添加节点动作,两个动作，一个添加节点，把父节点状态变成isparent=1
    @Override
    public TaotaoResult createContentCat(long parentId, String name) {
        //插入数据
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setParentId(parentId);
        tbContentCategory.setName(name);
        tbContentCategory.setStatus(1);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setIsParent(false);
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setUpdated(new Date());
        contentCategoryMapper.insert(tbContentCategory);
        //更新父节点动作
        TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parent.getIsParent()) {
            parent.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(parent);
        }
        return TaotaoResult.ok(tbContentCategory);
    }

    @Override
    public TaotaoResult deleteContentCat(long id) {
        TbContentCategory son = contentCategoryMapper.selectByPrimaryKey(id);
        Long parentId = son.getParentId();
        contentCategoryMapper.deleteByPrimaryKey(id);
        TbContentCategoryExample example = new TbContentCategoryExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> tbContentCategories = contentCategoryMapper.selectByExample(example);
        if (tbContentCategories.size() == 0) {
            TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
            parent.setIsParent(false);
            contentCategoryMapper.updateByPrimaryKeySelective(parent);
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
