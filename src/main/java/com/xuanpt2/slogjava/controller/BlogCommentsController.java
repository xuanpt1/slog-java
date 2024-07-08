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

    @PostMapping("/deleteCommentByCoid")
    public boolean deleteCommentByCoid(int coid){
        return false;
    }

    @PostMapping("/deleteCommentBatchByCoid")
    public boolean deleteCommentBatchByCoid(List<Integer> coidList){
        return false;
    }

    @PostMapping("/insertComment")
    public boolean insertComment(BlogComments blogComment){
        return false;
    }

    @PostMapping("/updateComment")
    public boolean updateComment(BlogComments blogComment){
        return false;
    }
}
