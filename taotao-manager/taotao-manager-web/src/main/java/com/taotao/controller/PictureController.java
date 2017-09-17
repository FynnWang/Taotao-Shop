package com.taotao.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.swing.StringUIClientPropertyKey;

import java.util.Map;

@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String uploadPicture(MultipartFile uploadFile) {

        Map resultMap = pictureService.uploadPicture(uploadFile);
        String jsonString = JSONUtils.toJSONString(resultMap);
        return jsonString;
    }
}
