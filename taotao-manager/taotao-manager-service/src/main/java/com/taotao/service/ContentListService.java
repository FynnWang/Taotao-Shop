package com.taotao.service;

import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.pojo.TaotaoResult;

/**
 * Created by Administrator on 2017/4/15.
 */
public interface ContentListService {

    EUDateGridResult contentList(int page, int rows, Long categoryId);


}
