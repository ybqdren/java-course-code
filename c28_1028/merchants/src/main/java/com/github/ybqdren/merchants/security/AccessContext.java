package com.github.ybqdren.merchants.security;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/13 12:43
 * @package com.github.ybqdren.merchants.security
 * @description
 *
 * <h1> 保存每一个线程携带的 Token 信息 </h1>
 * <p> ThreadLocal 非常轻量级，用来保存当前线程中少量的数据 </p>
 **/
public class AccessContext {
    /** 存储 token 中的信息 **/
    private static final ThreadLocal<String> token = new ThreadLocal<String>();

    /**
     * token 一个 ThreadLocal 对象，用来保存 token
     * @return
     */
    public static String getToken(){
        return token.get();
    }

    /**
     * 设置 token 信息
     * @param tokenString
     */
    public static void setToken(String tokenString){
        token.set(tokenString);
    }

    /**
     * 清除线程中的 token 信息，为了安全保证了及时的销毁
     */
    public static void clearAccessKey(){
        token.remove();
    }
}
