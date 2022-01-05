package org.github.ybqdren.common.util;

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
        UserDetailsService userDetailsService = SpringContextH
    }
}
