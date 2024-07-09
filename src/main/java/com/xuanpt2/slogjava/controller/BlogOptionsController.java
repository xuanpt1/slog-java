package com.xuanpt2.slogjava.controller;

import com.xuanpt2.slogjava.entity.BlogOptions;
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
@RequestMapping("/blogOptions")
public class BlogOptionsController {

    /**
     * 通过配置名称获取某项配置参数
     *
     * @param
     * name 该项配置的名称 传入String
     *
     * @return
     * 该项配置 作为BlogOptions对象转为json返回
     */
    @PostMapping("/getOptionByName")
    public BlogOptions getOptionByName(String name){
        return null;
    }

    /**
     * 更新某项配置
     * 暂不考虑新增配置
     *
     * @param
     * blogOptions 前端传入json格式BlogOptions对象
     *
     * @return
     * 返回boolean值代表修改成功与否
     */
    @PostMapping("/updateOption")
    public boolean updateOption(BlogOptions blogOptions){
        return false;
    }

}
