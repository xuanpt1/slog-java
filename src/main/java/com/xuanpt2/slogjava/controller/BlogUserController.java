package com.xuanpt2.slogjava.controller;

import com.xuanpt2.slogjava.entity.BlogUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xuanpt2
 * @since 2024-7-4
 */
@RestController
@RequestMapping("/blogUser")
public class BlogUserController {
    @PostMapping("/saveUser")
    public boolean saveUser(BlogUser blogUser){
        return false;
    }

    @PostMapping("/updateUser")
    public boolean updateUser(BlogUser blogUser){
        return false;
    }

    @PostMapping("/removeUser")
    public boolean removeUser(BlogUser blogUser){
        return false;
    }
}
