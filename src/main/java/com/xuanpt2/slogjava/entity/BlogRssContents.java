package com.xuanpt2.slogjava.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("blog_rss_contents")
public class BlogRssContents implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 该条RSS item的编号
     */
    @TableId(value = "rcid", type = IdType.AUTO)
    private Integer rcid;

    /**
     * 该条item所属的RSS链接编号
     */
    private Integer rid;

    /**
     * 标题
     */
    private String title;

    /**
     * 作者名
     */
    private String author;

    /**
     * 原文链接
     */
    private String link;

    /**
     * 该条RSS的description，用longtext防御发布者RSS不规范
     */
    private String text;

    /**
     * 该条item创建的具体时间，用于定期删除
     */
    private LocalDateTime createdTime;

    public Integer getRcid() {
        return rcid;
    }

    public void setRcid(Integer rcid) {
        this.rcid = rcid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "BlogRssContents{" +
        "rcid = " + rcid +
        ", rid = " + rid +
        ", title = " + title +
        ", author = " + author +
        ", link = " + link +
        ", text = " + text +
        ", createdTime = " + createdTime +
        "}";
    }
}
