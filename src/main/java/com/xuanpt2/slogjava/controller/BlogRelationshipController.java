package com.xuanpt2.slogjava.controller;

import com.xuanpt2.slogjava.entity.BlogRelationship;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  已弃用
 * </p>
 * 不单独处理文章-分类对应关系，转移到文章相关信息内一并处理。
 *
 * @author xuanpt2
 * @since 2024-7-4
 */
@RestController
@RequestMapping("/blogRelationship")
public class BlogRelationshipController {

//    @GetMapping("/test")
//    public BlogRelationship test(){
//        BlogRelationship br = new BlogRelationship();
//        br.setCid(1);
//        br.setMid(5);
//        return br;
//    }

    /**
     * 新增一项博文-分类对应关系
     *
     * @param
     * blogRelationship 前端传入json格式BlogRelationship对象
     * @return
     * boolean代表新增是否成功
     */
    @PostMapping("/saveRelationship")
    public boolean saveRelationship(BlogRelationship blogRelationship){
        return false;
    }

    /**
     * 移除一项博文-分类对应关系
     *
     * @param
     *
     * @return
     */
    @PostMapping("/removeRelationship")
    public boolean removeRelationship(BlogRelationship blogRelationship){
        return false;
    }

    /**
     * 批量添加对应关系
     *
     * @param
     * blogRelationshipList 前端传入List<BlogRelationship>代表需
     * @return
     */
    @PostMapping("/saveRelationshipBatch")
    public boolean saveRelationshipBatch(List<BlogRelationship> blogRelationshipList){
        return false;
    }

    /**
     * 批量删除对应关系
     *
     */
}
