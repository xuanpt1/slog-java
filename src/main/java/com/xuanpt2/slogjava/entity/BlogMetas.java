package com.xuanpt2.slogjava.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xuanpt2
 * @since 2024-7-4
 */
@TableName("blog_metas")
public class BlogMetas implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    @TableId(value = "mid", type = IdType.AUTO)
    private Integer mid;

    /**
     * 该分类名称
     */
    private String name;

    /**
     * 该分类描述
     */
    private String description;

    /**
     * 该分类父类别的ID
     */
    private Integer pmid;

    /**
     * 该分类文章总数
     */
    private Integer count;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPmid() {
        return pmid;
    }

    public void setPmid(Integer pmid) {
        this.pmid = pmid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BlogMetas{" +
        "mid = " + mid +
        ", name = " + name +
        ", description = " + description +
        ", pmid = " + pmid +
        ", count = " + count +
        "}";
    }
}
