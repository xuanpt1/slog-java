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
@TableName("blog_groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BlogGroups implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 本地群组ID RSS订阅归入默认群组0
     */
    @TableId(value = "group_id", type = IdType.AUTO)
    private Integer groupId;

    /**
     * 标识加入群组的唯一URL，由创建者生成
     */
    private String groupUrl;

    /**
     * 群组名称
     */
    private String groupTitle;

    /**
     * 群组用户数量
     */
    private Integer count;

    @Override
    public String toString() {
        return "BlogGroups{" +
        "groupId = " + groupId +
        ", groupUrl = " + groupUrl +
        ", groupTitle = " + groupTitle +
        ", count = " + count +
        "}";
    }
}
