package com.xuanpt2.slogjava.entity;

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
@TableName("blog_options")
public class BlogOptions implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 该项配置的名称
     */
    @TableId("name")
    private String name;

    /**
     * 该项配置的具体取值
     */
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BlogOptions{" +
        "name = " + name +
        ", value = " + value +
        "}";
    }
}
