package com.github.ybqdren.passbook.log;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhao wen
 * @since 1.0.0
 *
 * <h1> 日志生成器 </h1>
 **/

@Slf4j
public class LogGenerator {
    /**
     * <h2> 生成 log </h2>
     * @param request {@link HttpServletRequest} 获取用户请求中的信息
     * @param userId  用户 id
     * @param action  {@link LogConstants} 日志类型
     * @param info    日志信息，可以是 null
     */
    public static void genLog(HttpServletRequest request , Long userId , String action , Object info){
        log.info(
                JSON.toJSONString(
                        new LogObject(action,userId,System.currentTimeMillis(),request.getRemoteAddr(),info)
                )
        );
    }
}
