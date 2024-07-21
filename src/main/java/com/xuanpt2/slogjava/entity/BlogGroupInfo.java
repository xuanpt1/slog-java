package com.xuanpt2.slogjava.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author xuanpt2
 * @since 2024-7-4
 */
@TableName("blog_group_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
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


    public static List<Integer> toRidList(List<BlogGroupInfo> groupInfoList){
        List<Integer> list = new ArrayList<Integer>();

        for (BlogGroupInfo info :
                groupInfoList) {
            list.add(info.getRid());
        }
        return list;
    }

    public static List<Integer> toGroupIdList(List<BlogGroupInfo> groupInfoList){
        List<Integer> list = new ArrayList<Integer>();

        for (BlogGroupInfo info :
                groupInfoList) {
            list.add(info.getGroupId());
        }
        return list;
    }

    @Override
    public String toString() {
        return "BlogGroupInfo{" +
        "groupId = " + groupId +
        ", rid = " + rid +
        "}";
    }
}
