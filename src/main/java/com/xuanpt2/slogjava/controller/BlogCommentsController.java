package com.xuanpt2.slogjava.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuanpt2.slogjava.entity.BlogComments;
import com.xuanpt2.slogjava.service.IBlogCommentsService;
import com.xuanpt2.slogjava.vo.TResponseVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
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
    private IBlogCommentsService blogCommentsService;

    @GetMapping("/getAllComments")
    public List<BlogComments> getAllComments(){
        return null;
    }

    @PostMapping("/getCommentsByCid")
    public TResponseVo<List<BlogComments>> getCommentsByCid(int cid){
        if (blogCommentsService.getOptById(cid).isPresent()){
            return TResponseVo.success(blogCommentsService.list(new QueryWrapper<BlogComments>().eq("cid",cid)));
        }else {
            return TResponseVo.success(new ArrayList<BlogComments>());
        }
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
