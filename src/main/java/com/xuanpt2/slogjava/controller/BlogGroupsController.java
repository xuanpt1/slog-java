package com.xuanpt2.slogjava.controller;

import com.xuanpt2.slogjava.entity.BlogGroups;
import com.xuanpt2.slogjava.entity.BlogRelationship;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/blogGroups")
public class BlogGroupsController {
    /**
     * 获取所有群组信息
     * @param
     *
     * @return
     */
    @GetMapping("/getAllGoups")
    public List<BlogGroups> getAllGroups(){
        return null;
    }

    /**
     * 加入群组
     * @param
     *
     * @return
     */
    @PostMapping("/saveGroup")
    public boolean saveGroup(String url){
        return false;
    }

    /**
     * 退出群组
     * @param
     *
     * @return
     */
    @PostMapping("removeGroupById")
    public boolean removeGroupById(int groupId){
        return false;
    }

    @GetMapping("/listTest")
    public List<BlogRelationship> test(){
        BlogRelationship b1 = new BlogRelationship();
        b1.setMid(1);
        b1.setCid(2);

        BlogRelationship b2 = new BlogRelationship();
        b2.setMid(3);
        b2.setCid(4);

        List<BlogRelationship> lb = new ArrayList<BlogRelationship>();
        lb.add(b1);
        lb.add(b2);
        return lb;
    }
}
