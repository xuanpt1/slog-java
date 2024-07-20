package com.xuanpt2.slogjava.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xuanpt2
 * @since 2024-7-4
 */
@TableName("blog_contents")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogContents implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    @TableId(value = "cid", type = IdType.AUTO)
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
     * 点击量
     */
    private Integer clicks;


    @Override
    public String toString() {
        return "BlogContents{" +
        "cid = " + cid +
        ", title = " + title +
        ", slug = " + slug +
        ", createdTime = " + createdTime +
        ", modifiedTime = " + modifiedTime +
        ", touchTime = " + touchTime +
        ", text = " + text +
        ", authorId = " + authorId +
        ", status = " + status +
        ", commentsNum = " + commentsNum +
        ", allowComment = " + allowComment +
        ", description = " + description +
        "}";
    }
}
