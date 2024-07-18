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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
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

    /**
     * 用户角色（配合SpringSecurity
     */
    private String urole;


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
