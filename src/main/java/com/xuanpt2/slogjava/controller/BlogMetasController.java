package com.xuanpt2.slogjava.controller;

import com.xuanpt2.slogjava.entity.BlogMetas;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Controller
@RequestMapping("/blogMetas")
public class BlogMetasController {

    @GetMapping("/getAllMetas")
    public List<BlogMetas> getAllMetas(){
        return null;
    }

    @PostMapping("/createMeta")
    public boolean createMeta(){
        return false;
    }

    @PostMapping("/saveOrUpdateMeta")
    public boolean saveOrUpdateMeta(BlogMetas blogMeta){
        return false;
    }

    @PostMapping("/deleteMetaById")
    public boolean deleteMetaById(int mid){
        return false;
    }


}
