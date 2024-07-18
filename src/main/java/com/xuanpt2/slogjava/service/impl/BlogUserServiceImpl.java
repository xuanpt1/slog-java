package com.xuanpt2.slogjava.service.impl;

import com.xuanpt2.slogjava.entity.BlogUser;
import com.xuanpt2.slogjava.mapper.BlogUserMapper;
import com.xuanpt2.slogjava.service.IBlogUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuanpt2
 * @since 2024-7-4
 */
@Service
public class BlogUserServiceImpl extends ServiceImpl<BlogUserMapper, BlogUser> implements IBlogUserService {

    @Override
    public BlogUser getBlogUserByUname(String uname) {

        return null;
    }
}
