package com.xuanpt2.slogjava.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.xuanpt2.slogjava.entity.BlogRssContents;
import com.xuanpt2.slogjava.entity.BlogRssSub;
import com.xuanpt2.slogjava.service.IBlogRssContentsService;
import com.xuanpt2.slogjava.service.IBlogRssSubService;
import com.xuanpt2.slogjava.utils.HttpRestUtils;
import com.xuanpt2.slogjava.utils.RssUtils;
import com.xuanpt2.slogjava.vo.TResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@RequestMapping("/blogRssContents")
public class BlogRssContentsController {
    @Autowired
    private IBlogRssSubService blogRssSubService;

    @Autowired
    private IBlogRssContentsService blogRssContentsService;

//    @GetMapping("test")
//    public String test(){
//        try {
//            SyndFeed feed = RssUtils.test();
//            System.out.println(feed);
//            return HttpRestUtils.get("https://2heng.xin/feed", new LinkedMultiValueMap<>());
//        }catch (Exception e){
//            return e.getMessage();
//        }
//    }

    @GetMapping("/getAllRssContents")
    public TResponseVo<List<BlogRssContents>> getAllRssContents(){
        try {
            return TResponseVo.success(blogRssContentsService.list());
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }

    @PostMapping("/updateRssContents")
    public TResponseVo<Boolean> updateRssContents(@RequestBody Map<String , Object> map){
        String url = (String) map.get("url");
        try {
            if ("*".equals(url)){
                List<BlogRssSub> subList = blogRssSubService.list();
                for (BlogRssSub sub :
                        subList) {
                    blogRssContentsService.updateUrl(sub.getRurl(), sub.getRid());
                }
            }else {
                BlogRssSub blogRssSub = blogRssSubService.getOne(new QueryWrapper<BlogRssSub>().eq("rurl", url));
                blogRssContentsService.updateUrl(blogRssSub.getRurl(), blogRssSub.getRid());
            }
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
        return TResponseVo.success(true);
    }

    @PostMapping("/updateContentsByRid")
    public TResponseVo<Boolean> updateContentByRid(@RequestBody Map<String, Object> map){
        BlogRssSub sub = blogRssSubService.getById((Serializable) map.get("rid"));

        boolean flag = blogRssContentsService.updateUrl(sub.getRurl(),sub.getRid());
        return flag? TResponseVo.success(true) : TResponseVo.error(500, "更新失败喵");
    }
}
