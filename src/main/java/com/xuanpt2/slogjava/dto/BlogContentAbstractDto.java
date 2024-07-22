package com.xuanpt2.slogjava.dto;

import com.xuanpt2.slogjava.entity.BlogContents;
import com.xuanpt2.slogjava.utils.RegexUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuanpt2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BlogContentAbstractDto {

    /**
     * 文章ID
     */
    private int cid;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 发布日期
     */
    private LocalDateTime createdTime;

    /**
     * 文章摘要/简略信息
     */
    private String text;

    /**
     * 头图url（若博文中有插图
     */
    private String imgUrl;

    /**
     * 文章包含的tag
     */
    private List<BlogMetaDto> tagList;



    public BlogContentAbstractDto(BlogContents blogContent, List<BlogMetaDto> blogMetaDtoList){
        this.cid = blogContent.getCid();
        this.title = blogContent.getTitle();
        this.text = (blogContent.getText().length() > 210) ? blogContent.getText().substring(0,200) : blogContent.getText();
        this.createdTime = blogContent.getCreatedTime();
        this.tagList = blogMetaDtoList;
        this.imgUrl = RegexUtils.getMdImgUrl(blogContent.getText()).iterator().next();
    }

}
