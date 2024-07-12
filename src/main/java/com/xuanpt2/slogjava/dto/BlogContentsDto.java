package com.xuanpt2.slogjava.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xuanpt2.slogjava.entity.BlogContents;
import com.xuanpt2.slogjava.utils.RegexUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BlogContentsDto {
    /**
     * 文章id
     */
    private Integer cid;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 特殊标识
     */
    private String slug;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifiedTime;

    /**
     * 最终阅读时间，用于判断dirty
     */
    private LocalDateTime touchTime;

    /**
     * 文章内容  以MarkDown格式
     */
    private String text;

    /**
     * 即uid，文章创建者id  他源则为0
     */
    private Integer authorId;

    /**
     * 文章状态  发布/草稿
     */
    private String status;

    /**
     * 评论数
     */
    private Integer commentsNum;

    /**
     * 1 允许/0 拒绝
     */
    private String allowComment;

    /**
     * 文章摘要
     */
    private String description;

    /**
     * 文章包含的tag
     */
    private List<BlogMetaDto> tagList;

    public static BlogContents toBlogContents(BlogContentsDto blogContentsDto){
        BlogContents blogContents = new BlogContents();
        blogContents.setCid(blogContentsDto.getCid());
        blogContents.setTitle(blogContentsDto.getTitle());
        blogContents.setSlug(blogContentsDto.getSlug());
        blogContents.setCreatedTime(blogContentsDto.getCreatedTime());
        blogContents.setModifiedTime(blogContentsDto.getModifiedTime());
        blogContents.setTouchTime(blogContentsDto.getTouchTime());
        blogContents.setText(blogContentsDto.getText());
        blogContents.setAuthorId(blogContentsDto.getAuthorId());
        blogContents.setStatus(blogContentsDto.getStatus());
        blogContents.setCommentsNum(blogContentsDto.getCommentsNum());
        blogContents.setAllowComment(blogContentsDto.getAllowComment());
        blogContents.setDescription(blogContentsDto.getDescription());
        return blogContents;
    }

    public BlogContentsDto(BlogContents blogContents){
        this.cid = blogContents.getCid();
        this.text = blogContents.getText();
        this.createdTime = blogContents.getCreatedTime();
        this.authorId = blogContents.getAuthorId();
        this.slug = blogContents.getSlug();
        this.allowComment = blogContents.getAllowComment();
        this.commentsNum = blogContents.getCommentsNum();
        this.description = blogContents.getDescription();
        this.modifiedTime = blogContents.getModifiedTime();
        this.touchTime = blogContents.getTouchTime();
        this.title = blogContents.getTitle();
        this.status = blogContents.getStatus();
        this.tagList = new ArrayList<>();
    }

    public BlogContentsDto(BlogContents blogContent, List<BlogMetaDto> blogMetaDtoList){
        this(blogContent);
        this.tagList = blogMetaDtoList;
    }
}
