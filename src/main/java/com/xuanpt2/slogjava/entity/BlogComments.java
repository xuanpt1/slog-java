package com.xuanpt2.slogjava.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
@TableName("blog_comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BlogComments implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论ID
     */
    @TableId(value = "coid", type = IdType.AUTO)
    private Integer coid;

    /**
     * 所属文章ID
     */
    private Integer cid;

    /**
     * 发表时间
     */
    private LocalDateTime createdTime;

    /**
     * 发表人用户名
     */
    private String author;

    /**
     * 发表人ID，外源则置0
     */
    private Integer authorId;

    /**
     * 发表人邮箱
     */
    private String mail;

    /**
     * 发表人留下的主页链接
     */
    private String url;

    /**
     * 发表人ip
     */
    private String ip;

    /**
     * 发表人所用agent
     */
    private String agent;

    /**
     * 评论内容
     */
    private String text;

    /**
     * 评论状态
     */
    private String status;

    /**
     * 评论的父评论（即所回复的评论）id
     */
    private Integer pcoid;

    /**
     * 赞
     */
    private Integer like;

    /**
     * 踩
     */
    private Integer dislike;

    @Override
    public String toString() {
        return "BlogComments{" +
        "coid = " + coid +
        ", cid = " + cid +
        ", createdTime = " + createdTime +
        ", author = " + author +
        ", authorId = " + authorId +
        ", mail = " + mail +
        ", url = " + url +
        ", ip = " + ip +
        ", agent = " + agent +
        ", text = " + text +
        ", status = " + status +
        "}";
    }
}
