package com.xuanpt2.slogjava.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xuanpt2.slogjava.entity.BlogGroups;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BlogGroupDto {
    /**
     * 本地群组ID RSS订阅归入默认群组0
     */
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

    /**
     * 群组内所有URL
     */
    private List<String> feeds;

    public BlogGroupDto(BlogGroups group, List<String> feeds){
        this.groupUrl = group.getGroupUrl();
        this.groupTitle = group.getGroupTitle();
        this.groupId = group.getGroupId();
        this.count = group.getCount();
        this.feeds = feeds;
    }
}
