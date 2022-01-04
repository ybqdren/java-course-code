package org.github.ybqdren.common.enumeration;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/4 12:21
 * @package org.github.ybqdren.common.enumeration
 * @description 用户等级
 **/
public enum UserLevel {
    /**
     * 游客即可访问
     */
    TOURIST,

    /**
     * 登录才可访问
     */
    LOGIN,

    /**
     * 登录有权限才可访问
     */
    GROUP,

    /**
     * 管理员权限
     */
    ADMIN,

    /**
     * 令牌刷新
     */
    REFRESH
}
