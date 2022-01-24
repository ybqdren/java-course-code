package com.github.ybqdren.repository;

import org.springframework.data.domain.AuditorAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/24 15:18
 * @package com.github.ybqdren.repository
 * @description
 * 实现 AuditorAware 接口告诉 JPA 当前的用户是谁
 *
 * 通过实现 AuditorAware 接口的 getCurrentAuditor() 方法 ** 告诉 JPA 当前用户是谁。 **
 * 下面这种有很多的实现方法，此处只列举了两种方式。
 **/
public class MyAuditoAware implements AuditorAware<Integer> {

    @Override
    public Optional<Integer> getCurrentAuditor() {
        // 第一种方法：通过 Spring Security 取，如果我们继承了 spring 的 Security，我们直接通过如下方法即可获得当前请求的用户 ID
/*        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            return null;
        }
        return ((LoginUserInfo) authentication.getPrincipal()).getUser().getId();*/

        // 第二种方法：通过 Request 取，通过 request 里面取或者 session 里面取
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return (Optional<Integer>) servletRequestAttributes.getRequest().getSession().getAttribute("userId");
    }
}
