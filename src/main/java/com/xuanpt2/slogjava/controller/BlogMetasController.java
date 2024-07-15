package com.xuanpt2.slogjava.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuanpt2.slogjava.entity.BlogMetas;
import com.xuanpt2.slogjava.entity.BlogRelationship;
import com.xuanpt2.slogjava.service.IBlogMetasService;
import com.xuanpt2.slogjava.service.IBlogRelationshipService;
import com.xuanpt2.slogjava.vo.TResponseVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
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
@RequestMapping("/blogMetas")
public class BlogMetasController {
    private IBlogMetasService blogMetasService;

    private IBlogRelationshipService blogRelationshipService;

    @GetMapping("/getAllMetas")
    public TResponseVo<List<BlogMetas>> getAllMetas(){
        try {
            return TResponseVo.success(blogMetasService.list());
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }


    @PostMapping("/saveMeta")
    public TResponseVo<Boolean> saveMeta(@RequestBody BlogMetas blogMetas){
        try {
            boolean flag = blogMetasService.save(blogMetas);
            return flag? TResponseVo.success(true): TResponseVo.error(500, "保存失败");
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }

    @PostMapping("/updateMeta")
    public TResponseVo<Boolean> updateMeta(@RequestBody BlogMetas blogMeta){
        try {
            boolean flag = blogMetasService.saveOrUpdate(blogMeta);
            return flag? TResponseVo.success(true): TResponseVo.error(500, "保存失败");
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }

    @PostMapping("/removeMetaById")
    public TResponseVo<Boolean> removeMetaById(@RequestBody Map<String, Object> map){
        try {
            boolean flag1 = blogMetasService.removeById((Serializable) map.get("mid"));
            boolean flag2 = blogRelationshipService.remove(new QueryWrapper<BlogRelationship>().eq("mid",
                    (Serializable) map.get("mid")));
            return (flag1 && flag2) ? TResponseVo.success(true) : TResponseVo.error(500, "移除失败");
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }


}
