package com.xuanpt2.slogjava.service.impl;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.xuanpt2.slogjava.entity.BlogRssContents;
import com.xuanpt2.slogjava.mapper.BlogRssContentsMapper;
import com.xuanpt2.slogjava.service.IBlogRssContentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuanpt2.slogjava.utils.RssUtils;
import com.xuanpt2.slogjava.vo.TResponseVo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuanpt2
 * @since 2024-7-4
 */
@Service
public class BlogRssContentsServiceImpl extends ServiceImpl<BlogRssContentsMapper, BlogRssContents> implements IBlogRssContentsService {
    @Override
    public boolean updateUrl(String url, int rid) {
        SyndFeed feed = RssUtils.parse(url);
        if (feed != null) {
            String siteTitle = feed.getTitle();
            for (SyndEntry entry :
                    feed.getEntries()) {
                BlogRssContents content = new BlogRssContents().setLink(entry.getLink())
                        .setAuthor(entry.getAuthor())
                        .setText(entry.getDescription().getValue())
                        .setRid(rid)
                        .setTitle(entry.getTitle())
                        .setCreatedTime(LocalDateTime.now());
                if (content.getAuthor().isEmpty()) {
                    content.setAuthor(siteTitle);
                }
                this.saveOrUpdate(content);
            }
        }else {
            return false;
        }
        return true;
    }

}
