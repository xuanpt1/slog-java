package com.xuanpt2.slogjava.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuanpt2.slogjava.dto.BlogGroupDto;
import com.xuanpt2.slogjava.dto.BlogMetaDto;
import com.xuanpt2.slogjava.entity.*;
import com.xuanpt2.slogjava.service.IBlogGroupInfoService;
import com.xuanpt2.slogjava.service.IBlogGroupsService;
import com.xuanpt2.slogjava.service.IBlogRssContentsService;
import com.xuanpt2.slogjava.service.IBlogRssSubService;
import com.xuanpt2.slogjava.vo.TResponseVo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.ArrayList;
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
@RequestMapping("/blogGroups")
public class BlogGroupsController {
    @Autowired
    private IBlogGroupsService blogGroupsService;

    @Autowired
    private IBlogGroupInfoService blogGroupInfoService;

    @Autowired
    private IBlogRssSubService blogRssSubService;

    @Autowired
    private IBlogRssContentsService blogRssContentsService;

    /**
     * 获取所有群组信息
     * @param
     *
     * @return
     */
    @GetMapping("/getAllGroups")
    public TResponseVo<List<BlogGroupDto>> getAllGroups(){
        List<BlogGroups> groupsList = blogGroupsService.list();
        List<BlogGroupDto> groupDtoList = new ArrayList<>();

        try {
            for (BlogGroups group :
                    groupsList) {
                List<String> urls =
                        BlogRssSub.toUrlList(blogRssSubService.listByIds(BlogGroupInfo.toRidList(
                                blogGroupInfoService.list(new QueryWrapper<BlogGroupInfo>().eq("group_id", group.getGroupId())))));
                groupDtoList.add(new BlogGroupDto(group, urls));
            }
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }


        return TResponseVo.success(groupDtoList);
    }

    @PostMapping("/getGroupContentsByGroupId")
    public TResponseVo<List<BlogRssContents>> getGroupContentsByGroupId(@RequestBody Map<String, Object> map){
        List<BlogRssContents> list = new ArrayList<>();
        try {
             List<Integer> ridList =
                     BlogGroupInfo.toRidList(blogGroupInfoService.list(new QueryWrapper<BlogGroupInfo>().eq("group_id",
                    (Serializable) map.get("groupId"))));

            for (Integer rid :
                    ridList) {
                list.addAll(blogRssContentsService.list(new QueryWrapper<BlogRssContents>().eq("rid", rid)));
            }
            return TResponseVo.success(list);
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }

    /**
     * 创建群组
     * @param
     *
     * @return
     */
    @PostMapping("/saveGroup")
    public TResponseVo<String> saveGroup(@RequestBody Map<String, Object> map){
        String title = (String) map.get("title");
        String uri = RandomStringUtils.randomAlphanumeric(10);
        try {
            blogGroupsService.save(new BlogGroups().setGroupTitle(title).setGroupUrl(uri).setCount(1));
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
        return TResponseVo.success(uri);
    }

    /**
     * 退出群组
     * @param
     *
     * @return
     */
    @PostMapping("removeGroupById")
    public TResponseVo<Boolean> removeGroupById(@RequestBody Map<String, Object> map){
        Integer groupId = (Integer) map.get("group_id");
        try {
            blogGroupsService.removeById(groupId);
            blogRssSubService.removeBatchByIds(BlogGroupInfo.toRidList(blogGroupInfoService.list(new QueryWrapper<BlogGroupInfo>().eq("group_id", groupId))));
            blogGroupInfoService.remove(new QueryWrapper<BlogGroupInfo>().eq("group_id", groupId));
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }

        return TResponseVo.success(true);
    }


    @GetMapping("/join/{uri}")
    public BlogGroupDto joinGroup(@PathVariable String uri){
        BlogGroupDto blogGroupDto = new BlogGroupDto();
        BlogGroups group = blogGroupsService.getOne(new QueryWrapper<BlogGroups>().eq("group_url",uri));
        List<String> urls =
                BlogRssSub.toUrlList(blogRssSubService.listByIds(
                        BlogGroupInfo.toRidList(blogGroupInfoService.list(new QueryWrapper<BlogGroupInfo>().eq("group_id", group.getGroupId())))));
        group.setCount(group.getCount() + 1);
        blogGroupsService.updateById(group);
        return new BlogGroupDto(group, urls);
    }


}
