package com.github.ybqdren.merchants.security;

import com.github.ybqdren.merchants.constant.Constants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/13 12:47
 * @package com.github.ybqdren.merchants.security
 * @description
 *
 * <h1> 权限拦截器 </h1>
 **/

@Component
public class AuthCheckInterceptor implements HandlerInterceptor {

    /**
     * 在 http 请求真正的处理之前，拦截器会做一个拦截和处理
     *
     * 此处是每一个商户都使用了同一个 token ，在真正的企业级开发中肯定是一个商户对应一个 token，然后通过 token 映射的方式去保存：
     * A 公司  token 为 aa
     * B 公司  token 为 bb
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 请求在携带了 token 信息时才可以通过校验
        String token = request.getHeader(Constants.TOKEN_STRING);   // 在 header 中 查找 key 为 token 的 value

        if(StringUtils.isEmpty(token)){
            throw  new Exception("Header 中缺少 " + Constants.TOKEN_STRING + "!");
        }

        if(!token.equals(Constants.TOKEN)){
            throw  new Exception("Head 中" + Constants.TOKEN_STRING + "错误");
        }

        // 将 token 信息放进 ThreadLocal 实例对象中
        AccessContext.setToken(token);
        return true;
    }

    /**
     * 在 http 请求处理完成之后去拦截
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 与 postHandler 不同之处：postHanler 在抛出异常的情况下就不会拦截成功，afterCompletion 即使是抛出了异常也会执行
     * 确定了 http 请求执行成功之后，才会去拦截信息，一般会在 此方法中执行一些清理问题
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清理线程中的 token 信息
        AccessContext.clearAccessKey();
    }
}
