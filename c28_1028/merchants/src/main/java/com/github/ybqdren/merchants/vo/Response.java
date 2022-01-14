package com.github.ybqdren.merchants.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/14 12:34
 * @package com.github.ybqdren.merchants.vo
 * @description
 *
 * <h1> 通用的响应对象 </h1>
 * <p> 响应给前台 前端 </p>
 *
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    /** 错误码，正确返回 0  **/
    private Integer errorCode = 0;

    /** 错误信息，正确返回空字符串 **/
    private String errorMsg = "";

    /** 返回值对象 **/
    private Object data;

    /**
     * <h2> 正确的响应构造函数 </h2>
     * @param data 返回值对象
     */
    public Response(Object data){
        this.data = data;
    }
}
