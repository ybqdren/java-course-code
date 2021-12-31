package org.github.ybqdren.common.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/31 16:17
 * @package org.github.ybqdren.common.interceptor
 * @description  ref https://www.cnblogs.com/weianlai/p/11358768.html
 **/
public class MyHandlerInterceptorAdapter extends HandlerInterceptorAdapter {

    // 预处理的回调方法，实现处理器的预处理（如检查登录），第三个参数为响应的处理器，自定义 Controller
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }

    // 后处理回调方法，实现处理器的后处理（但在渲染视图之前），此时我们可以通过 modelAndView（模型和视图对象）
    // 对模型数据进行处理或对视图进行处理，modelAndView 也可能为 null
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    // 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间
    // 还可以进行一些资源清理，类似于 try-catch-finally 中的 finally，但仅调用处理器执行链中
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
