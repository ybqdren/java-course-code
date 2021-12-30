package org.github.ybqdren.common.interceptor;

import org.github.ybqdren.common.annotation.LogPermissionMeta;
import org.github.ybqdren.common.annotation.Logger;
import org.github.ybqdren.common.interfaces.LoggerResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/30 16:24
 * @package org.github.ybqdren.common.interfaces
 * @description
 **/
public class LogIntereptor implements AsyncHandlerInterceptor {

    @Autowired
    private LoggerResolver loggerResolver;

    /**
     * 后置处理
     * @param request       请求
     * @param response      响应
     * @param handler       处理器
     * @param modelAndView  视图
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("-------- 尝试去拦截 -------------------");

        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Logger logger = method.getAnnotation(Logger.class);
            if(logger != null){
                LogPermissionMeta meta = method.getAnnotation(LogPermissionMeta.class);
                // 从 request 、response 和 modelAndView 中解析 template 和外部 peoperties
                loggerResolver.handle(meta,logger,request,response);
            }
        }
    }
}
