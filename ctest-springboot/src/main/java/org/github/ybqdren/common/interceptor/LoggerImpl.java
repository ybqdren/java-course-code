package org.github.ybqdren.common.interceptor;

import org.github.ybqdren.common.annotation.LogPermissionMeta;
import org.github.ybqdren.common.annotation.Logger;
import org.github.ybqdren.common.interfaces.LoggerResolver;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/30 17:19
 * @package org.github.ybqdren.common.interceptor
 * @description
 **/

@Component
public class LoggerImpl implements LoggerResolver {
    @Override
    public void handle(LogPermissionMeta meta, Logger logger, HttpServletRequest request, HttpServletResponse response) {

    }
}
