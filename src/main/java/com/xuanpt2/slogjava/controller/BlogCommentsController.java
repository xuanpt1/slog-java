package com.xuanpt2.slogjava.controller;

import com.xuanpt2.slogjava.entity.BlogComments;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
@RequestMapping("/blogComments")
public class BlogCommentsController {
    @GetMapping("/getAllComments")
    public List<BlogComments> getAllComments(){
        return null;
    }

    @PostMapping("/getCommentsByCid")
    public List<BlogComments> getCommentsByCid(int cid){
        return null;
    }

    @PostMapping("/removeCommentByCoid")
    public boolean removeCommentByCoid(int coid){
        return false;
    }

    @PostMapping("/removeCommentBatchByCoid")
    public boolean removeCommentBatchByCoid(List<Integer> coidList){
        return false;
    }

    @PostMapping("/saveComment")
    public boolean saveComment(BlogComments blogComment){
        return false;
    }

    @PostMapping("/updateComment")
    public boolean updateComment(BlogComments blogComment){
        return false;
    }
}
