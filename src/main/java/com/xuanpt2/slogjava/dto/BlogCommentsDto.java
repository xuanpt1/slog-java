package com.xuanpt2.slogjava.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xuanpt2.slogjava.entity.BlogComments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BlogCommentsDto {
    /**
     * 评论ID
     */
    private Integer coid;

    /**
     * 所属文章ID
     */
    private Integer cid;

    /**
     * 发表时间
     */
    private LocalDateTime createdTime;

    /**
     * 发表人用户名
     */
    private String author;

    /**
     * 发表人邮箱
     */
    private String mail;

    /**
     * 发表人留下的主页链接
     */
    private String url;

    /**
     * 父评论ID
     */
    private Integer pcoid;

    /**
     * 评论内容
     */
    private String text;

    /**
     * 赞
     */
    private Integer like;

    /**
     * 踩
     */
    private Integer dislike;

    /**
     * 该评论的回复
     */
    List<BlogCommentsDto> replyList;

    public BlogCommentsDto(BlogComments blogComment){
        this.setCoid(blogComment.getCoid());
        this.setCid(blogComment.getCid());
        this.setCreatedTime(blogComment.getCreatedTime());
        this.setAuthor(blogComment.getAuthor());
        this.setMail(blogComment.getMail());
        this.setUrl(blogComment.getUrl());
        this.setPcoid(blogComment.getPcoid());
        this.setText(blogComment.getText());
        this.setLike(blogComment.getLikeCount());
        this.setDislike(blogComment.getDislikeCount());
        this.replyList = new ArrayList<>();
    }

    public static List<BlogCommentsDto> toDtoList(List<BlogComments> blogCommentsList){
        List<BlogCommentsDto> list = new ArrayList<>();
        for (BlogComments comment :
                blogCommentsList) {
            list.add(new BlogCommentsDto(comment));
        }
        return list;
    }
    
    public static List<BlogCommentsDto> toDtoTree(List<BlogCommentsDto> dtoList){
        Map<Integer, BlogCommentsDto> index = new HashMap<>(dtoList.size());
        for (BlogCommentsDto dto :
                dtoList) {
            index.put(dto.getCoid(), dto);
        }

        List<BlogCommentsDto> list = new ArrayList<>();
        for (BlogCommentsDto dto :
                dtoList) {
            if (dto.getPcoid() == 0){
                list.add(dto);
            }else {
                index.get(dto.getPcoid()).getReplyList().add(dto);
            }
        }

        return list;
    }
}
