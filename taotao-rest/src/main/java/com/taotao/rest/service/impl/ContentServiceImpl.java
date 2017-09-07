package com.taotao.rest.service.impl;

import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import com.taotao.rest.dao.JedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    private static final Logger logger = LoggerFactory.getLogger(ContentServiceImpl.class);

    @Autowired
    private JedisClient jedisClient;

    public List<TbContent> getContentList(long contentId) {
        try {
            String cacheString = jedisClient.hget("CONTENT_LIST_HASH_KEY", String.valueOf(contentId));
            if (!StringUtils.isBlank(cacheString)) {
                List<TbContent> contentList = JsonUtils.jsonToList(cacheString, TbContent.class);
                return contentList;
            }
        } catch (Exception e) {
            logger.error("[ItemCatServiceImpl] [getContentList] jedisClient fail to get : " + e.getMessage());
        }
        List<TbContent> contentList = new ArrayList<>();
        try {
            String cacheString = JsonUtils.objectToJson(contentList);
            long CONTENT_LIST_HASH_KEY = jedisClient.hset("CONTENT_LIST_HASH_KEY", String.valueOf(contentId), cacheString);

        } catch (Exception e) {
            logger.error("[ItemCatServiceImpl] [getContentList] jedisClient fail to set : " + e.getMessage());
        }
        return contentList;
    }
}
