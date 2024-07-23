package com.xuanpt2.slogjava.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuanpt2.slogjava.dto.BlogGroupDto;
import com.xuanpt2.slogjava.dto.BlogMetaDto;
import com.xuanpt2.slogjava.entity.*;
import com.xuanpt2.slogjava.service.IBlogGroupInfoService;
import com.xuanpt2.slogjava.service.IBlogGroupsService;
import com.xuanpt2.slogjava.service.IBlogRssContentsService;
import com.xuanpt2.slogjava.service.IBlogRssSubService;
import com.xuanpt2.slogjava.utils.HttpRestUtils;
import com.xuanpt2.slogjava.vo.TResponseVo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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
                List<Integer> ridList = BlogGroupInfo.toRidList(
                        blogGroupInfoService.list(new QueryWrapper<BlogGroupInfo>().eq("group_id",
                                group.getGroupId())));
                List<String> urls = ridList.isEmpty() ? new ArrayList<>() :
                        BlogRssSub.toUrlList(blogRssSubService.listByIds(ridList));

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
     * 为群组添加feed
     * @param map rurl:feed链接      groupId:群组Id
     * @return  保存链接后的群组dto对象
     */
    @PostMapping("/putRssSubByRurl")
    public TResponseVo<BlogGroupDto> putRssSubByRurl(@RequestBody Map<String, Object> map){
        String rurl = (String) map.get("rurl");
        try {
            Integer rid =
                    blogRssSubService.getOneOpt(new QueryWrapper<BlogRssSub>().eq("rurl", rurl)).map(BlogRssSub::getRid)
                            .orElseGet(()-> {
                                blogRssSubService.saveOrUpdate(new BlogRssSub().setRurl(rurl));
                                return blogRssSubService.getOne(new QueryWrapper<BlogRssSub>().eq("rurl",rurl)).getRid();
                            });
            BlogGroups group = blogGroupsService.getById((Serializable) map.get("groupId"));
            Integer gid = group.getGroupId();
            blogGroupInfoService.saveOrUpdateByMultiId(new BlogGroupInfo().setGroupId(gid).setRid(rid));


            List<Integer> ridList = BlogGroupInfo.toRidList(
                    blogGroupInfoService.list(new QueryWrapper<BlogGroupInfo>().eq("group_id",
                            gid)));
            List<String> urls = ridList.isEmpty() ? new ArrayList<>() :
                    BlogRssSub.toUrlList(blogRssSubService.listByIds(ridList));
            return TResponseVo.success(new BlogGroupDto(group, urls));
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }

    @PostMapping("/putRssSubByRid")
    public TResponseVo<BlogGroupDto> putRssSubByRid(@RequestBody Map<String, Object> map){
        try {
            BlogRssSub sub = blogRssSubService.getById((Serializable) map.get("rid"));
            BlogGroups group = blogGroupsService.getById((Serializable) map.get("groupId"));
            Integer gid = group.getGroupId();
            Integer rid = sub.getRid();
            blogGroupInfoService.saveOrUpdateByMultiId(new BlogGroupInfo().setGroupId(gid).setRid(rid));


            List<Integer> ridList = BlogGroupInfo.toRidList(
                    blogGroupInfoService.list(new QueryWrapper<BlogGroupInfo>().eq("group_id",
                            gid)));
            List<String> urls = ridList.isEmpty() ? new ArrayList<>() :
                    BlogRssSub.toUrlList(blogRssSubService.listByIds(ridList));
            return TResponseVo.success(new BlogGroupDto(group, urls));
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }

    @PostMapping("/removeRssSubFromGroupByRid")
    public TResponseVo<BlogGroupDto> removeRssSubFromGroupByRid(@RequestBody Map<String, Object> map){
        try {
            BlogRssSub sub = blogRssSubService.getById((Serializable) map.get("rid"));
            BlogGroups group = blogGroupsService.getById((Serializable) map.get("groupId"));
            Integer gid = group.getGroupId();
            Integer rid = sub.getRid();

            if (blogGroupInfoService.deleteByMultiId(new BlogGroupInfo(gid,rid))){
                List<Integer> ridList = BlogGroupInfo.toRidList(
                        blogGroupInfoService.list(new QueryWrapper<BlogGroupInfo>().eq("group_id",
                                gid)));
                List<String> urls = ridList.isEmpty() ? new ArrayList<>() :
                        BlogRssSub.toUrlList(blogRssSubService.listByIds(ridList));
                return TResponseVo.success(new BlogGroupDto(group, urls));
            }else {
                return TResponseVo.error(500, "移除失败，未知错误喵");
            }
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
    @PostMapping("/removeGroupById")
    public TResponseVo<Boolean> removeGroupById(@RequestBody Map<String, Object> map){
        Integer groupId = (Integer) map.get("groupId");
        try {
            blogGroupsService.removeById(groupId);
            blogRssSubService.removeBatchByIds(BlogGroupInfo.toRidList(blogGroupInfoService.list(new QueryWrapper<BlogGroupInfo>().eq("group_id", groupId))));
            blogGroupInfoService.remove(new QueryWrapper<BlogGroupInfo>().eq("group_id", groupId));
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }

        return TResponseVo.success(true);
    }

    @PostMapping("/joinGroup")
    public TResponseVo<BlogGroupDto> joinGroup(@RequestBody Map<String ,Object> map){
        String url = (String) map.get("url");
        try {
            String response = HttpRestUtils.get(url, null);
            BlogGroupDto groupDto = JSON.parseObject(response, BlogGroupDto.class);
            System.out.println(groupDto);
            groupDto.setGroupId(null);

            BlogGroups group = groupDto.toBlogGroups();
            //List<BlogRssSub> subs = groupDto.getFeeds().stream().map(BlogRssSub::setRurl).toList();
            List<BlogRssSub> subList = new ArrayList<>();
            for (String feed :
                    groupDto.getFeeds()) {
                subList.add(new BlogRssSub().setRurl(feed));
            }
            System.out.println(subList);
            List<Integer> ridList = new ArrayList<>();

            if (!blogGroupsService.exists(new QueryWrapper<BlogGroups>().eq("group_url", group.getGroupUrl()))){
                blogGroupsService.saveOrUpdate(group);
            }
            Integer groupId = blogGroupsService.getOne(new QueryWrapper<BlogGroups>().eq("group_url",
                    group.getGroupUrl())).getGroupId();

            for (BlogRssSub sub :
                    subList) {
                if (!blogRssSubService.exists(new QueryWrapper<BlogRssSub>().eq("rurl", sub.getRurl()))) {
                    blogRssSubService.saveOrUpdate(sub);
                }
                ridList.add(blogRssSubService.getOne(new QueryWrapper<BlogRssSub>().eq("rurl",
                        sub.getRurl())).getRid());
            }


            //List<Integer> ridList = blogRssSubService.listObjs(new QueryWrapper<BlogRssSub>().select("rid").in
            // ("rurl",groupDto.getFeeds()));

            List<BlogGroupInfo> groupInfoList = new ArrayList<>();
            for (Integer rid:
                 ridList) {
                groupInfoList.add(new BlogGroupInfo(groupId, rid));
            }

            System.out.println(groupInfoList);
            blogGroupInfoService.saveOrUpdateBatchByMultiId(groupInfoList);
            return new TResponseVo<>(200, groupDto,response);
        } catch (IOException e) {
            return TResponseVo.error(500, e.getMessage());
        }

    }

    @GetMapping("/join/{uri}")
    public BlogGroupDto join(@PathVariable String uri){
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
