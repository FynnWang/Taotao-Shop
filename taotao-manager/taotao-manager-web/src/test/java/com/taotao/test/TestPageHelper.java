package com.taotao.test;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.Page;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

public class TestPageHelper {

    @Test
    public void testPageHelper() {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:spring/applicationContext*.xml");
        TbContentMapper mapper = applicationContext.getBean(TbContentMapper.class);
        TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(89L);
        List<TbContent> list = mapper.selectByExample(example);
        System.out.println(list.size());
		PageHelper.startPage(1, 10);
//
//        List<TbContent> list = mapper.selectByExample(example);
//        for (TbItem tbItem : list) {
//            System.out.println(tbItem);
//        }
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        System.out.println(list instanceof Page);
        System.out.println("共有商品信息：" + pageInfo.getTotal());
    }
}
