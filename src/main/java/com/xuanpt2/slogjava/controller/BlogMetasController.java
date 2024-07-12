package com.xuanpt2.slogjava.controller;

import com.xuanpt2.slogjava.entity.BlogMetas;
import com.xuanpt2.slogjava.service.IBlogMetasService;
import com.xuanpt2.slogjava.vo.TResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

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
@Controller
@RequestMapping("/blogMetas")
public class BlogMetasController {
    private IBlogMetasService blogMetasService;

    @GetMapping("/getAllMetas")
    public List<BlogMetas> getAllMetas(){
        return blogMetasService.list();
    }

    @PostMapping("/getMetaBatchByCid")
    public TResponseVo<List<BlogMetas>> getMetaBatchByCid(@RequestBody Map<String, Object> map){
        return null;
    }

    @PostMapping("/saveMeta")
    public boolean saveMeta(BlogMetas blogMetas){
        return false;
    }

    @PostMapping("/saveOrUpdateMeta")
    public boolean saveOrUpdateMeta(BlogMetas blogMeta){
        return false;
    }

    @PostMapping("/removeMetaById")
    public boolean removeMetaById(int mid){
        return false;
    }


}
