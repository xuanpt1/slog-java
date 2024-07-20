package com.xuanpt2.slogjava.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.xuanpt2.slogjava.dto.BlogContentAbstractDto;
import com.xuanpt2.slogjava.dto.BlogContentsDto;
import com.xuanpt2.slogjava.dto.BlogMetaDto;
import com.xuanpt2.slogjava.entity.BlogContents;
import com.xuanpt2.slogjava.entity.BlogMetas;
import com.xuanpt2.slogjava.entity.BlogRelationship;
import com.xuanpt2.slogjava.mapper.BlogMetasMapper;
import com.xuanpt2.slogjava.service.IBlogContentsService;
import com.xuanpt2.slogjava.service.IBlogMetasService;
import com.xuanpt2.slogjava.service.IBlogRelationshipService;
import com.xuanpt2.slogjava.service.impl.BlogRssContentsServiceImpl;
import com.xuanpt2.slogjava.utils.RegexUtils;
import com.xuanpt2.slogjava.vo.TResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xuanpt2
 * @since 2024-7-4
 */
@RestController
@RequestMapping("/blogContents")
public class BlogContentsController {
    @Autowired
    private IBlogContentsService blogContentsService;

    @Autowired
    private IBlogMetasService blogMetasService;

    @Autowired
    private IBlogRelationshipService blogRelationshipService;

    /**
     * 获取所有博文的大致信息
     * @return TResponseVo<List<BlogContentAbstractDto>> 所有博文的大致信息
     */
    @GetMapping("/getAllContents")
    public TResponseVo<List<BlogContentAbstractDto>> getAllContents(){
        List<BlogContentAbstractDto> blogContentAbstractDtoList = new ArrayList<>();
        List<BlogContents> blogContentsList = blogContentsService.list();

        QueryWrapper<BlogRelationship> blogRelationshipQueryWrapper = new QueryWrapper<BlogRelationship>();

        try {
            for (BlogContents blogContents :
                    blogContentsList) {
                blogRelationshipQueryWrapper.clear();

                blogRelationshipQueryWrapper.eq("cid", blogContents.getCid());
                List<BlogRelationship> blogRelationshipList = blogRelationshipService.list(blogRelationshipQueryWrapper);
                List<BlogMetaDto> blogMetaDtoList = new ArrayList<>();
                if (!blogRelationshipList.isEmpty()){
                    blogMetaDtoList =
                            BlogMetaDto.toDtoList(blogMetasService.listByIds(BlogRelationship.toMidList(blogRelationshipList)));
                }

                blogContentAbstractDtoList.add(new BlogContentAbstractDto(blogContents,blogMetaDtoList));
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return TResponseVo.error(500,e.getMessage());
        }

        return TResponseVo.success(blogContentAbstractDtoList);
    }

    @GetMapping("/getClicksIncrease")
    public TResponseVo<Integer> getClicksIncrease(@RequestBody Map<String ,Object> map){
        try {
            BlogContents content = blogContentsService.getById((Serializable) map.get("cid"));
            int i =content.getClicks() + 1;
            content.setClicks(i);
            blogContentsService.saveOrUpdate(content);
            return TResponseVo.success(i);
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }

    /**
     * 通过Id获取文章包含tag的全部信息
     * @param map cid
     * @return 返回带有List<Meta> 的ContentAbsDTO->TResponseVo
     */
    @PostMapping("/getContentById")
    public TResponseVo<BlogContentsDto> getContentById(@RequestBody Map<String, Object> map) {

        try {
            BlogContents blogContent = blogContentsService.getById((Serializable) map.get("cid"));
            List<BlogRelationship> blogRelationshipList =
                    blogRelationshipService.list(new QueryWrapper<BlogRelationship>().eq("cid", map.get("cid")));
            List<BlogMetaDto> blogMetaDtoList =
                    BlogMetaDto.toDtoList(blogMetasService.listByIds(BlogRelationship.toMidList(blogRelationshipList)));
            return TResponseVo.success(new BlogContentsDto(blogContent, blogMetaDtoList));
        }catch (Exception e){
            return TResponseVo.error(500,e.getMessage());
        }

    }
    
    @PostMapping("/getContentsAbsByMetaId")
    public TResponseVo<List<BlogContentAbstractDto>> getContentsByMetaId(@RequestBody List<Integer> midList){
        List<Integer> cidList = new ArrayList<>();
        List<BlogContentAbstractDto> blogContentAbstractDtoList = new ArrayList<>();
        QueryWrapper<BlogRelationship> blogRelationshipQueryWrapper = new QueryWrapper<BlogRelationship>();

        try {
            for (Integer mid :
                    midList) {
                cidList.addAll(BlogRelationship.toCidList(blogRelationshipService.list(new QueryWrapper<BlogRelationship>().eq("mid"
                        , mid))));
            }
            List<Integer> distCidList = cidList.stream().distinct().toList();

            for (Integer cid :
                    distCidList) {
                blogRelationshipQueryWrapper.clear();

                List<BlogMetaDto> blogMetaDtoList =
                        BlogMetaDto.toDtoList(blogMetasService.listByIds(BlogRelationship.toMidList(blogRelationshipService.list(new QueryWrapper<BlogRelationship>().eq("cid", cid)))));
                BlogContents content = blogContentsService.getById(cid);
                blogContentAbstractDtoList.add(new BlogContentAbstractDto(content, blogMetaDtoList));
            }

            return TResponseVo.success(blogContentAbstractDtoList);
        }catch (Exception e){
            return TResponseVo.error(500, e.getMessage());
        }
    }

    @PostMapping("/updateContent")
    public TResponseVo<Boolean> updateContent(@RequestBody BlogContentsDto blogContentsDto){
        try {
            blogContentsService.saveOrUpdate(BlogContentsDto.toBlogContents(blogContentsDto));
            blogRelationshipService.remove(new QueryWrapper<BlogRelationship>().eq("cid",blogContentsDto.getCid()));
            for (BlogMetaDto blogMetaDto :
                    blogContentsDto.getTagList()) {
                blogRelationshipService.saveOrUpdateByMultiId(new BlogRelationship(blogContentsDto.getCid(),
                        blogMetaDto.getId()));
            }
            return TResponseVo.success(true);
        }catch (Exception e){
            return TResponseVo.error(500,e.getMessage());
        }

    }

    @PostMapping("/removeContentById")
    public TResponseVo<Boolean> removeContentById(@RequestBody Map<String, Object> map){
        try {
            boolean flag = blogContentsService.removeById((Serializable) map.get("cid"));
            boolean flag2 = blogRelationshipService.remove(new QueryWrapper<BlogRelationship>().eq("cid",
                    (Serializable) map.get("cid")));

            return (flag && flag2) ? TResponseVo.success(true) : TResponseVo.error(500,"移除文章失败");
        }catch (Exception e){
            return TResponseVo.error(500,e.getMessage());
        }
    }

    @PostMapping("/removeContentBatchById")
    public boolean removeContentBatchById(List<Integer> cidList){
        return false;
    }

    @PostMapping("/saveContent")
    public TResponseVo<Boolean> saveContent(@RequestBody BlogContentsDto blogContentsDto){
        try {
            blogContentsService.saveOrUpdate(BlogContentsDto.toBlogContents(blogContentsDto));
            for (BlogMetaDto blogMetaDto :
                    blogContentsDto.getTagList()) {
                blogRelationshipService.saveOrUpdateByMultiId(new BlogRelationship(blogContentsDto.getCid(),
                        blogMetaDto.getId()));
            }
            return TResponseVo.success(true);
        }catch (Exception e){
            return TResponseVo.error(500,e.getMessage());
        }
    }

    @GetMapping("/testGet")
    public BlogContentAbstractDto testGet(){
//        List<BlogContentAbstractDto> blogContentAbstractDtoList1 = new ArrayList<BlogContentAbstractDto>();
//
//        BlogMetaDto blogMetaDto1 = new BlogMetaDto(1,"test01","just a test tag 01");
//        BlogMetaDto blogMetaDto2 = new BlogMetaDto(2,"test02","just a test tag 02");
//        BlogMetaDto blogMetaDto3 = new BlogMetaDto(3,"test03","just a test tag 03");
//
//        List<BlogMetaDto> blogMetaDtoList1 = new ArrayList<BlogMetaDto>();
//        List<BlogMetaDto> blogMetaDtoList2 = new ArrayList<BlogMetaDto>();
//        blogMetaDtoList1.add(blogMetaDto1);
//        blogMetaDtoList1.add(blogMetaDto2);
//        blogMetaDtoList2.add(blogMetaDto2);
//        blogMetaDtoList2.add(blogMetaDto3);
//
//        String text = "texttexttext no any image";
//        String imgUrl = RegexUtils.getMdImgUrl(text).iterator().next();
//        LocalDateTime time = LocalDateTime.now();
//
//        blogContentAbstractDtoList1.add(new BlogContentAbstractDto(1, "testContent1", time, text,  imgUrl,
//                blogMetaDtoList2));
//        blogContentAbstractDtoList1.add(new BlogContentAbstractDto(2, "testContent2", time, text,  imgUrl,
//                blogMetaDtoList2));
//
//        return blogContentAbstractDtoList1;

        BlogContents blogContents = blogContentsService.getOptById(2).get();
        ArrayList<BlogMetaDto> blogMetaDtos = new ArrayList<>();

        return new BlogContentAbstractDto(blogContents,blogMetaDtos);
    }
}
