package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCatListService {

    List<EUTreeNode> getContentCatList(Long parentId);

    TaotaoResult createContentCat(long parentId, String name);

    TaotaoResult deleteContentCat(long id);

    TaotaoResult renameContentCat(Long id, String name);

}
