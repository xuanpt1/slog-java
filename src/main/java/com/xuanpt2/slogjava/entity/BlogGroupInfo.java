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
@TableName("blog_group_info")
public class BlogGroupInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 组ID
     */
    @MppMultiId
    @TableField("group_id")
    private Integer groupId;

    /**
     * RSS ID
     */
    @MppMultiId
    @TableField("rid")
    private Integer rid;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "BlogGroupInfo{" +
        "groupId = " + groupId +
        ", rid = " + rid +
        "}";
    }
}
