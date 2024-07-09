package com.xuanpt2.slogjava.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

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
}
