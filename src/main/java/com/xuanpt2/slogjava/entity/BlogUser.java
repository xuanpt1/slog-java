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
@TableName("blog_user")
public class BlogUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 本地用户的id
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    /**
     * 用户的登录用户名
     */
    private String uname;

    /**
     * 用户的登录密码
     */
    private String upwd;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 最终登录时间
     */
    private LocalDateTime loggedTime;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getLoggedTime() {
        return loggedTime;
    }

    public void setLoggedTime(LocalDateTime loggedTime) {
        this.loggedTime = loggedTime;
    }

    @Override
    public String toString() {
        return "BlogUser{" +
        "uid = " + uid +
        ", uname = " + uname +
        ", upwd = " + upwd +
        ", mail = " + mail +
        ", nickname = " + nickname +
        ", createdTime = " + createdTime +
        ", loggedTime = " + loggedTime +
        "}";
    }
}
