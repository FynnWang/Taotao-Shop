package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface ItemParamService {

	TaotaoResult getItemCatByCId(long Cid);

	TaotaoResult insertItemParam(TbItemParam itemParam);
}
