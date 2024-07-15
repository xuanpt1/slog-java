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
@TableName("blog_relationship")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
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

//    public Integer getCid() {
//        return cid;
//    }
//
//    public void setCid(Integer cid) {
//        this.cid = cid;
//    }
//
//    public Integer getMid() {
//        return mid;
//    }
//
//    public void setMid(Integer mid) {
//        this.mid = mid;
//    }

    public static List<Integer> toMidList(List<BlogRelationship> blogRelationshipList) {
        List<Integer> midList = new ArrayList<>();
        for (BlogRelationship blogRela :
                blogRelationshipList) {
            midList.add(blogRela.getMid());
        }
        return midList;
    }

    public static List<Integer> toCidList(List<BlogRelationship> blogRelationshipList) {
        List<Integer> cidList = new ArrayList<>();
        for (BlogRelationship blogRela :
                blogRelationshipList) {
            cidList.add(blogRela.getCid());
        }
        return cidList;
    }

    @Override
    public String toString() {
        return "BlogRelationship{" +
        "cid = " + cid +
        ", mid = " + mid +
        "}";
    }
}
