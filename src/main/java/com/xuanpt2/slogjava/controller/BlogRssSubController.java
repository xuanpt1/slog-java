package com.xuanpt2.slogjava.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuanpt2.slogjava.entity.BlogGroupInfo;
import com.xuanpt2.slogjava.entity.BlogRssContents;
import com.xuanpt2.slogjava.entity.BlogRssSub;
import com.xuanpt2.slogjava.service.IBlogGroupInfoService;
import com.xuanpt2.slogjava.service.IBlogRssContentsService;
import com.xuanpt2.slogjava.service.IBlogRssSubService;
import com.xuanpt2.slogjava.vo.TResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.security.Key;
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
@RequestMapping("/blogRssSub")
public class BlogRssSubController {
    @Autowired
    private IBlogRssSubService blogRssSubService;

    @Autowired
    private IBlogRssContentsService blogRssContentsService;

    @Autowired
    private IBlogGroupInfoService blogGroupInfoService;

    /**
     * 添加RSS订阅
     *
     */
    @PostMapping("/saveRssSub")
    public TResponseVo<Boolean> saveRssSub(@RequestBody Map<String, Object> map){
        try {
            boolean flag = blogRssSubService.save(new BlogRssSub().setRurl((String) map.get("url")));
            return flag ? TResponseVo.success(true) : TResponseVo.error(500, "保存失败喵");
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }

    /**
     * 移除RSS订阅
     */
    @PostMapping("/removeRssSubById")
    public TResponseVo<Boolean> removeRssSub(@RequestBody Map<String, Object> map){
        try {
            boolean flag1 = blogRssSubService.removeById((Serializable) map.get("rid"));
            boolean flag2 = blogRssContentsService.remove(new QueryWrapper<BlogRssContents>().eq("rid",map.get("rid")));
            boolean flag3 = blogGroupInfoService.remove(new QueryWrapper<BlogGroupInfo>().eq("rid", map.get("rid")));
            return (flag1 && flag2 && flag3) ? TResponseVo.success(true) : TResponseVo.error(500, "删除失败喵" + "\nflag1" +
                    ":" + flag1 + "\nflag2: " + flag2 +"\nflag3: " + flag3);
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }

    @PostMapping("updateRssSub")
    public boolean updateRssSub(BlogRssSub blogRssSub){
        return false;
    }

    @GetMapping("/getAllRssSub")
    public TResponseVo<List<BlogRssSub>> getAllRssSub(){
        try {
            return TResponseVo.success(blogRssSubService.list());
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }
}
