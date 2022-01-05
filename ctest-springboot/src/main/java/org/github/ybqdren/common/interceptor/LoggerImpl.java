package org.github.ybqdren.common.interceptor;

import org.github.ybqdren.common.annotation.PermissionMeta;
import org.github.ybqdren.common.annotation.Logger;
import org.github.ybqdren.common.interfaces.LoggerResolver;
import org.github.ybqdren.common.util.SecurityUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/30 17:19
 * @package org.github.ybqdren.common.interceptor
 * @description  {@link LoggerImpl} >> {@link LoggerResolver} -> {@link LogInterceptor}
 *                      ----- ({@link Logger}  and {@link PermissionMeta})
 **/

@Component
//@Primary
public class LoggerImpl implements LoggerResolver {

    /*
     * @param message    日志消息           Logger
     * @param permission 日志涉及的权限      PermissionMeta
     * @param userId     用户 id            PermissionMeta
     * @param username   用户名             PermissionMeta
     * @param method     请求（http）方法    HttpServletRequest
     * @param path       请求路径           HttpServletRequest
     * @param status     相应状态（http status）  HttpServletResponse
     */
    @Override
    public void handle(PermissionMeta meta, Logger logger, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("来到了 LoggerResolver 的实现类 ~");
        System.out.println(SecurityUtils.getCurrentUsername() +
                           "   "  +
                           logger.log() + " ~ " +
                           request.getMethod() +" ~ " +
                           request.getRequestURI() + " ~ " +
                           response.getStatus()
        );
    }
}
