package com.xuanpt2.slogjava.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuanpt2.slogjava.dto.BlogCommentsDto;
import com.xuanpt2.slogjava.entity.BlogComments;
import com.xuanpt2.slogjava.service.IBlogCommentsService;
import com.xuanpt2.slogjava.vo.TResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private IBlogCommentsService blogCommentsService;

    @GetMapping("/getAllComments")
    public TResponseVo<List<BlogComments>> getAllComments(){
        if (blogCommentsService.list().isEmpty()){
            return new TResponseVo<>(200, new ArrayList<BlogComments>(), "没有评论喵");
        }else {
            try {
                return TResponseVo.success(blogCommentsService.list());
            }catch (Exception e){
                return TResponseVo.error(500, e.getMessage());
            }
        }
    }

    @PostMapping("/getLiked")
    public TResponseVo<Integer> getLiked(@RequestBody Map<String, Object> map){
        try {
            BlogComments blogComments = blogCommentsService.getById((Serializable) map.get("coid"));
            int likeCount = blogComments.getLikeCount() + 1;
            blogComments.setLikeCount(likeCount);
            return TResponseVo.success(likeCount);
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }

    @PostMapping("/getDisliked")
    public TResponseVo<Integer> getDisliked(@RequestBody Map<String, Object> map){
        try {
            BlogComments blogComments = blogCommentsService.getById((Serializable) map.get("coid"));
            int dislikeCount = blogComments.getDislikeCount() + 1;
            blogComments.setDislikeCount(dislikeCount);
            return TResponseVo.success(dislikeCount);
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }

//    @GetMapping("/test")
//    public List<BlogCommentsDto> test(){
//        return BlogCommentsDto.toDtoTree(BlogCommentsDto.toDtoList(blogCommentsService.list(new QueryWrapper<BlogComments>().eq("cid",2))));
//    }

    /**
     *
     * @param map
     * @return
     *
     * TODO 添加按时间排序 修改DTO层，进行子评论嵌套
     */
    @PostMapping("/getCommentsByCid")
    public TResponseVo<List<BlogCommentsDto>> getCommentsByCid(@RequestBody Map<String ,Object> map){

        Serializable cid = (Serializable) map.get("cid");
        try {
            List<BlogComments> blogComments = blogCommentsService.list(new QueryWrapper<BlogComments>().eq("cid", cid));
            return TResponseVo.success(BlogCommentsDto.toDtoTree(BlogCommentsDto.toDtoList(blogComments)));
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }

    @PostMapping("/getCommentByCoid")
    public TResponseVo<BlogComments> getCommentByCoid(@RequestBody Map<String, Object> map){
        try {
            return blogCommentsService.getOptById((Serializable) map.get("coid")).map(TResponseVo::success).orElseGet(() -> TResponseVo.error(404, "没找到这条评论的信息喵"));
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }

    @PostMapping("/removeCommentByCoid")
    public TResponseVo<Boolean> removeCommentByCoid(@RequestBody Map<String, Object> map){
        try {
            boolean flag1 = blogCommentsService.removeById((Serializable) map.get("coid"));
            boolean flag2 = blogCommentsService.remove(new QueryWrapper<BlogComments>().eq("pcoid",
                    (Serializable) map.get("coid")));
            return (flag1 && flag2) ? TResponseVo.success(true) : TResponseVo.error(500, "移除失败喵");
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }

    }

//    暂时弃用
//    @PostMapping("/removeCommentBatchByCoid")
//    public boolean removeCommentBatchByCoid(List<Integer> coidList){
//        return false;
//    }

    @PostMapping("/saveComment")
    public TResponseVo<Boolean> saveComment(@Validated @RequestBody BlogComments blogComment){
        try {
            System.out.println(blogComment);
            boolean flag = blogCommentsService.save(blogComment);
            return flag ? TResponseVo.success(true) : TResponseVo.error(500, "保存失败喵");
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }

//    @PostMapping("/saveComment")
//    public TResponseVo<String> saveComment(@Validated @RequestBody BlogComments blogComment){
////        System.out.println(string);
////        BlogComments comments = JSON.parseObject(string, BlogComments.class);
////        System.out.println(comments);
//        return TResponseVo.success(blogComment.toString());
//    }

    @PostMapping("/updateComment")
    public TResponseVo<Boolean> updateComment(@RequestBody BlogComments blogComment){
        try {
            boolean flag = blogCommentsService.saveOrUpdate(blogComment);
            return flag ? TResponseVo.success(true) : TResponseVo.error(500, "更新失败喵");
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }
}
