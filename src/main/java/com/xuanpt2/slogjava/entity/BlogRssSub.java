package com.xuanpt2.slogjava.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
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

    @Override
    public String toString() {
        return "BlogRssSub{" +
        "rid = " + rid +
        ", rurl = " + rurl +
        "}";
    }
}
