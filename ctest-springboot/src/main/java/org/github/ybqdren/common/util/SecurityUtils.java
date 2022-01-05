package org.github.ybqdren.common.util;

import org.github.ybqdren.common.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/5 14:45
 * @package org.github.ybqdren.common.util
 * @description  获取当前登录的用户 （ base SpringBoot Security）
 **/
public class SecurityUtils {

    /**
     * 获取当前登录的用户
     * @return
     */
    public static UserDetails getCurrentUser(){
        // 在 IOC 容器中取出 UserDetailsService 的实例对象
        UserDetailsService userDetailsService = SpringContextHolder.getBean(UserDetailsService.class);
        return userDetailsService.loadUserByUsername(getCurrentUsername());
    }


    public static String getCurrentUsername(){
        // 调用 spring security 的 api 来获取当前的登录情况
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new BadRequestException(HttpStatus.UNAUTHORIZED, "当前登录状态过期");
        }

        if(authentication.getPrincipal() instanceof UserDetails){
            UserDetails userDetails = (UserDetails)  authentication.getPrincipal();
            return userDetails.getUsername();
        }

        throw new BadRequestException(HttpStatus.UNAUTHORIZED, "找不到当前登录的信息");
    }
}
