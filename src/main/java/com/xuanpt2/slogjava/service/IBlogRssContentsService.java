package com.xuanpt2.slogjava.service;

import com.xuanpt2.slogjava.entity.BlogRssContents;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xuanpt2
 * @since 2024-7-4
 */
public interface IBlogRssContentsService extends IService<BlogRssContents> {
    public boolean updateUrl(String url, int rid);
}
