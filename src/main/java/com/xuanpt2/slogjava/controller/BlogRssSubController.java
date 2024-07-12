package com.xuanpt2.slogjava.controller;

import com.xuanpt2.slogjava.entity.BlogRssSub;
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
@RequestMapping("/blogRssSub")
public class BlogRssSubController {
    /**
     * 添加RSS订阅
     *
     */
    @PostMapping("/saveRssSub")
    public boolean saveRssSub(String rssFeed){
        return false;
    }

    /**
     * 移除RSS订阅
     */
    @PostMapping("/removeRssSubById")
    public boolean removeRssSub(int rssId){
        return false;
    }

    @PostMapping("updateRssSub")
    public boolean updateRssSub(BlogRssSub blogRssSub){
        return false;
    }

    @GetMapping("/getAllRssSub")
    public List<BlogRssSub> getAllRssSub(){
        return null;
    }
}
