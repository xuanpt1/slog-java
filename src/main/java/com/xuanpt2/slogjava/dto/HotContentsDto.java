package com.xuanpt2.slogjava.dto;

import com.xuanpt2.slogjava.entity.BlogContents;
import com.xuanpt2.slogjava.utils.RegexUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuanpt2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class HotContentsDto {
    private Integer cid;

    private String text;

    private Integer clicks;

    public HotContentsDto(BlogContents blogContents){
        this.cid = blogContents.getCid();
        this.clicks = blogContents.getClicks();
        this.text = RegexUtils.getPureText(blogContents.getText()).substring(0,15);
    }

    public static List<HotContentsDto> toHotContentsDtoList(List<BlogContents> blogContentsList){
        List<HotContentsDto> hotContentsDtoList = new ArrayList<>();
        for (BlogContents content :
                blogContentsList) {
            hotContentsDtoList.add(new HotContentsDto(content));
        }
        return hotContentsDtoList;
    }
}
