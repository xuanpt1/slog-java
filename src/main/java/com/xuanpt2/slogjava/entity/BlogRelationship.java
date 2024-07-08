package com.xuanpt2.slogjava.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xuanpt2
 * @since 2024-7-4
 */
@TableName("blog_relationship")
public class BlogRelationship implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章ID
     */
    @MppMultiId
    @TableField("cid")
    private Integer cid;

    /**
     * 分类ID
     */
    @MppMultiId
    @TableField("mid")
    private Integer mid;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    @Override
    public String toString() {
        return "BlogRelationship{" +
        "cid = " + cid +
        ", mid = " + mid +
        "}";
    }
}
