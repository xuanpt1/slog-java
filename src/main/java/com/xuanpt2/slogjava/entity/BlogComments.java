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
@TableName("blog_comments")
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

    public Integer getCoid() {
        return coid;
    }

    public void setCoid(Integer coid) {
        this.coid = coid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
