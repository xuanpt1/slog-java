package com.xuanpt2.slogjava.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuanpt2.slogjava.entity.BlogUser;
import com.xuanpt2.slogjava.mapper.BlogUserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author xuanpt2
 */
@Service
public class CustomUserServiceImpl implements UserDetailsService {
    @Resource
    BlogUserMapper blogUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BlogUser blogUser = blogUserMapper.selectOne(new QueryWrapper<BlogUser>().eq("uname",username));
        return User
                .withUsername(blogUser.getUname())
                .password(blogUser.getUpwd())
                .build();
    }
}
