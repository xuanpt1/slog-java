package com.xuanpt2.slogjava.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuanpt2.slogjava.entity.BlogComments;
import com.xuanpt2.slogjava.service.IBlogCommentsService;
import com.xuanpt2.slogjava.vo.TResponseVo;
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

    @PostMapping("/getCommentsByCid")
    public TResponseVo<List<BlogComments>> getCommentsByCid(@RequestBody Map<String ,Object> map){
        Integer cid = (Integer) map.get("cid");
        if (blogCommentsService.getOptById(cid).isPresent()){
            try {
                return TResponseVo.success(blogCommentsService.list(new QueryWrapper<BlogComments>().eq("cid",cid)));
            }catch (Exception e){
                return TResponseVo.error(500, e.getMessage());
            }
        }else {
            return new TResponseVo<>(200, new ArrayList<BlogComments>(), "没有评论喵");
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
    public TResponseVo<Boolean> saveComment(@RequestBody BlogComments blogComment){
        try {
            boolean flag = blogCommentsService.save(blogComment);
            return flag ? TResponseVo.success(true) : TResponseVo.error(500, "保存失败喵");
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }

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
