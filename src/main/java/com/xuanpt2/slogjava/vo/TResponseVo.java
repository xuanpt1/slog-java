package com.xuanpt2.slogjava.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TResponseVo<E> {
    /**
     * 状态码
     */
    private int status;

    /**
     * 携带的数据
     */
    private E data;

    /**
     * 响应成功或者失败的信息
     */
    private String message;

    public static <T> TResponseVo<T> success(T data){
        return new TResponseVo<T>(200, data, "success");
    }

    public static <T> TResponseVo<T> error(int code, String msg){
        return new TResponseVo<T>(code, null, msg);
    }
}
