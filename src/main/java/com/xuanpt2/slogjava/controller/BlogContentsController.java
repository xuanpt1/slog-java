package com.xuanpt2.slogjava.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuanpt2.slogjava.dto.BlogContentAbstractDto;
import com.xuanpt2.slogjava.dto.BlogMetaDto;
import com.xuanpt2.slogjava.entity.BlogContents;
import com.xuanpt2.slogjava.entity.BlogMetas;
import com.xuanpt2.slogjava.entity.BlogRelationship;
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

//    @GetMapping("/testInit")
//    public BlogContents testInit(){
//        BlogContents blogContents = new BlogContents();
//        blogContents.setTitle("开始2");
//        blogContents.setAllowComment("1");
//        blogContents.setSlug("Default");
//        blogContents.setDescription("just a test content");
//        blogContents.setAuthorId(1);
//        blogContents.setStatus("public");
//        blogContents.setCreatedTime(LocalDateTime.now());
//        blogContents.setModifiedTime(LocalDateTime.now());
//        blogContents.setTouchTime(LocalDateTime.now());
//
//        blogContentsService.save(blogContents);
//        return blogContents;
//    }

    @GetMapping("/getAllContents")
    public List<BlogContents> getAllContents(){
        return null;
    }

    @PostMapping("/getContentById")
    public TResponseVo<BlogContentAbstractDto> getContentById(@RequestBody Map<String, Object> map) {
        Optional<BlogContents> optionalBlogContents = blogContentsService.getOptById((Serializable) map.get("cid"));
        BlogContents blogContent = optionalBlogContents.get();
        QueryWrapper<BlogRelationship> relationshipQueryWrapper = new QueryWrapper<>();
        relationshipQueryWrapper.eq("cid",blogContent.getCid());
        List<BlogRelationship> midList = blogRelationshipService.list(relationshipQueryWrapper);
        List<BlogMetas> blogMetas = new ArrayList<>();
        List<BlogMetaDto> blogMetaDtoList = new ArrayList<BlogMetaDto>();
        for (BlogRelationship rela:midList
             ) {
            blogMetas.addAll(blogMetasService.list(new QueryWrapper<BlogMetas>().eq("mid",rela.getMid())));
        }
        for (BlogMetas meta: blogMetas
             ) {
            blogMetaDtoList.add(meta.toDto());
        }

        System.out.println(midList);
        System.out.println(blogMetaDtoList);
        return TResponseVo.success(new BlogContentAbstractDto(blogContent,blogMetaDtoList));
    }

    @PostMapping("/updateContent")
    public boolean updateContent(BlogContents blogContent){
        return false;
    }

    @PostMapping("/removeContentById")
    public boolean removeContentById(int cid){
        return false;
    }

    @PostMapping("/removeContentBatchById")
    public boolean removeContentBatchById(List<Integer> cidList){
        return false;
    }

    @PostMapping("/saveContent")
    public TResponseVo<Boolean> saveContent(BlogContents blogContent){
        boolean flag = blogContentsService.save(blogContent);
        return flag? TResponseVo.success(true): TResponseVo.error(500,"save failed");
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
