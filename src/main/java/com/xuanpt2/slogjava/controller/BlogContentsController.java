package com.xuanpt2.slogjava.controller;

import com.xuanpt2.slogjava.entity.BlogContents;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xuanpt2
 * @since 2024-7-4
 */
@RestController
@RequestMapping("/blogContents")
public class BlogContentsController {
    @GetMapping("/getAllContents")
    public List<BlogContents> getAllContents(){
        return null;
    }

    @PostMapping("/getContentById")
    public BlogContents getContentById(int cid){
        return null;
    }

    @PostMapping("/updateContent")
    public boolean updateContent(BlogContents blogContent){
        return false;
    }

    @PostMapping("deleteContentById")
    public boolean deleteContentById(int cid){
        return false;
    }

    @PostMapping("deleteContentBatchById")
    public boolean deleteContentBatchById(List<Integer> cidList){
        return false;
    }

    @PostMapping("/saveOrUpdateContent")
    public boolean saveOrUpdateContent(BlogContents blogContent){
        return false;
    }
}
