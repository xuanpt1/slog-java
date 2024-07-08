package com.xuanpt2.slogjava.controller;

import com.xuanpt2.slogjava.entity.BlogRelationship;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xuanpt2
 * @since 2024-7-4
 */
@RestController
@RequestMapping("/blogRelationship")
public class BlogRelationshipController {

    @GetMapping("/test")
    public BlogRelationship test(){
        BlogRelationship br = new BlogRelationship();
        br.setCid(1);
        br.setMid(5);
        return br;
    }
}
