package com.taotao.service;

import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */
public interface ContentService {

    TaotaoResult contentAdd(TbContent content);

    EUDateGridResult getContentList(Long categoryId, int page, int rows);


}
