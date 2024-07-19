package com.xuanpt2.slogjava.controller;

import com.xuanpt2.slogjava.utils.OssUtils;
import com.xuanpt2.slogjava.vo.TResponseVo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/blogFile")
public class BlogFileController {
    @Resource
    private OssUtils ossUtils;

    Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/upload")
    public TResponseVo<String> upload(@RequestParam("tphotofile")MultipartFile tphotoFile){
        try {
            String simg = ossUtils.uploadFileAvatar(tphotoFile);
            logger.debug("上传文件：：：："+ simg);
            return new TResponseVo<String>().setMessage("文件成功上传到OSS中！ " + simg).setData("http://img.xuanpt2.com/"+simg).setStatus(200);
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }

    }
}
