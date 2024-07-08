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
@TableName("blog_rss_sub")
public class BlogRssSub implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 该条RSS订阅的ID
     */
    @TableId(value = "rid", type = IdType.AUTO)
    private Integer rid;

    /**
     * 该条RSS订阅的URL
     */
    private String rurl;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRurl() {
        return rurl;
    }

    public void setRurl(String rurl) {
        this.rurl = rurl;
    }

    @Override
    public String toString() {
        return "BlogRssSub{" +
        "rid = " + rid +
        ", rurl = " + rurl +
        "}";
    }
}
