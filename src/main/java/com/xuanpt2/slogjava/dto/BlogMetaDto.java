package com.xuanpt2.slogjava.dto;

import com.xuanpt2.slogjava.entity.BlogMetas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BlogMetaDto {
    private int id;
    private String name;
    private String description;

    public BlogMetaDto(BlogMetas blogMeta){
        this.id = blogMeta.getMid();
        this.name = blogMeta.getName();
        this.description = blogMeta.getDescription();
    }
}
