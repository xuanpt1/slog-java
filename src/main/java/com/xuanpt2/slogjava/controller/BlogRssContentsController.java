package com.xuanpt2.slogjava.controller;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.xuanpt2.slogjava.entity.BlogRssContents;
import com.xuanpt2.slogjava.entity.BlogRssSub;
import com.xuanpt2.slogjava.service.IBlogRssContentsService;
import com.xuanpt2.slogjava.service.IBlogRssSubService;
import com.xuanpt2.slogjava.utils.HttpRestUtils;
import com.xuanpt2.slogjava.utils.RssUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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

    @PostMapping("/saveTest")
    public boolean saveTest(){
        List<BlogRssSub> rssSubList = blogRssSubService.list();
        List<BlogRssContents> blogRssContentsList = new ArrayList<>();

        BlogRssSub sub1 = rssSubList.get(1);

        SyndFeed feed = RssUtils.parse(sub1.getRurl());
        for (SyndEntry entry :
                feed.getEntries()) {
            blogRssContentsService.save(new BlogRssContents().setLink(entry.getLink())
                    .setAuthor(entry.getAuthor())
                    .setText(entry.getDescription().getValue())
                    .setRid(sub1.getRid())
                    .setTitle(entry.getTitle())
                    .setCreatedTime(LocalDateTime.now()));
        }
        return true;
    }
}
