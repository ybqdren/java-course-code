package org.github.ybqdren.common.interfaces;

import org.github.ybqdren.common.annotation.LogPermissionMeta;
import org.github.ybqdren.common.annotation.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/30 17:03
 * @package org.github.ybqdren.common.interfaces
 * @description  行为日志记录
 **/

public interface LoggerResolver {
    /**
     * handle
     * @param meta 路由信息
     * @param logger logger 信息
     * @param request 请求
     * @param response 响应
     */
    void handle(LogPermissionMeta meta, Logger logger, HttpServletRequest request, HttpServletResponse response);
}
