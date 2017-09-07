package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbContent;
import com.taotao.rest.dao.JedisClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.controller.ItemCatController;
import com.taotao.rest.pojo.ItemCatNode;
import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    private static final Logger logger = LoggerFactory.getLogger(ItemCatServiceImpl.class);

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public ItemCatResult getItemCatResult() {
        logger.info("get into Service");

        ItemCatResult result = new ItemCatResult();
        result.setData(getItemCatList(0));
        return result;
    }

    public List<?> getItemCatList(long parentId) {
        logger.info("exercute getItemCatList");

        TbItemCatExample example = new TbItemCatExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = itemCatMapper.selectByExample(example);

        List resultList = new ArrayList<>();
        int count = 0;
        for (TbItemCat tbItemCat : list) {
            logger.info("start foreach");
            if (tbItemCat.getIsParent()) {
                ItemCatNode node = new ItemCatNode();
                node.setUrl("/products/" + tbItemCat.getId() + ".html");
                if (parentId == 0) {
                    node.setName("<a href='/products/" + tbItemCat.getId() + ".html'> " + tbItemCat.getName() + "</a>");
                } else {
                    node.setName(tbItemCat.getName());
                }
                node.setItem(getItemCatList(tbItemCat.getId()));

                resultList.add(node);
                count++;
                if (parentId == 0 && count >= 14) {
                    break;
                }
            } else {
                resultList.add("/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName());
            }
        }
        logger.info("exit Service");
        return resultList;
    }


}
