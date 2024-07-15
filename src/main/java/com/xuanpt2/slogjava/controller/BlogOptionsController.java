package com.xuanpt2.slogjava.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuanpt2.slogjava.entity.BlogOptions;
import com.xuanpt2.slogjava.service.IBlogOptionsService;
import com.xuanpt2.slogjava.vo.TResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
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
@RequestMapping("/blogOptions")
public class BlogOptionsController {
    @Autowired
    private IBlogOptionsService blogOptionsService;

    /**
     * 通过配置名称获取某项配置参数
     *
     * @param
     * map 该项配置的名称 传入{name : "name"} 格式的json
     * @return
     * 该项配置 作为BlogOptions对象转为json返回
     */
    @PostMapping("/getOptionByName")
    public TResponseVo<BlogOptions> getOptionByName(@RequestBody Map<String ,Object> map){
        try {
            return blogOptionsService.getOptById((Serializable) map.get("name")).map(TResponseVo::success)
             .orElseGet(() -> TResponseVo.error(404,"没找到喵"));
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
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
    public TResponseVo<Boolean> updateOption(@RequestBody BlogOptions blogOptions){
        try {
            boolean flag = blogOptionsService.saveOrUpdate(blogOptions);
            return flag ? TResponseVo.success(true) : TResponseVo.error(500, "修改失败喵");
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }

    }

}
