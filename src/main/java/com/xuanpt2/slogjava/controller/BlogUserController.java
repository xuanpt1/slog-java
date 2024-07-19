package com.xuanpt2.slogjava.controller;

import com.xuanpt2.slogjava.config.SecurityConfig;
import com.xuanpt2.slogjava.entity.BlogUser;
import com.xuanpt2.slogjava.service.IBlogUserService;
import com.xuanpt2.slogjava.vo.TResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.beans.Encoder;
import java.util.List;

/**
 * <p>
 *  用户管理
 * </p>
 *
 * @author xuanpt2
 * @since 2024-7-4
 */
@RestController
@RequestMapping("/blogUser")
public class BlogUserController {
    @Autowired
    private IBlogUserService blogUserService;

    @PostMapping("/saveUser")
    public TResponseVo<BlogUser> saveUser(@RequestBody BlogUser blogUser){
        try {
            String pwd = blogUser.getUpwd();
            String encodedPwd = new BCryptPasswordEncoder().encode(blogUser.getUpwd());
            blogUser.setUpwd(encodedPwd);
            blogUserService.save(blogUser);
            return new TResponseVo<>(200, blogUser, "密码为： " + pwd);
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }

    @GetMapping("/getAllUser")
    public TResponseVo<List<BlogUser>> getAllUser(){
        try {
            return TResponseVo.success(blogUserService.list());
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }

    @PostMapping("/updateUser")
    public TResponseVo<BlogUser> updateUser(@RequestBody BlogUser blogUser){
        try {
            String pwd = blogUser.getUpwd();
            String encodedPwd = new BCryptPasswordEncoder().encode(blogUser.getUpwd());
            blogUser.setUpwd(encodedPwd);
            blogUserService.updateById(blogUser);
            blogUser.setUpwd(pwd);
            return new TResponseVo<>(200, blogUser, "用户信息更改成功");
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }

    @PostMapping("/removeUser")
    public boolean removeUser(BlogUser blogUser){
        return false;
    }

    //生成初始用户时使用
//    @GetMapping("/test")
//    public String test(){
//        return new BCryptPasswordEncoder().encode("utakotoba");
//    }
}
