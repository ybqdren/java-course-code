package org.github.ybqdren.common.util;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/5 17:01
 * @package org.github.ybqdren.common.util
 * @description
 * 针对某些初始化方法，在 {@link SpringContextHolder} 初始化前
 *
 * 可提交一个回调任务
 *
 * 在 SpringContextHolder 初始化后，进行回调使用
 **/
public interface CallBack {

    /**
     * 回调执行方法
     */
    void excutor();

    // Java default关键字 https://www.cnblogs.com/liqing-weikeyuan/articles/7483260.html
    // default -> 虚拟扩展方法（Virtual extension methods） 指在接口内部包含了一些默认的方法实现（也就是接口中可以包含方法体）
    //                               从而使得接口在进行扩展的时候，不会破坏与接口相关的实现类代码。

    /**
     * 本回调任务名称
     * @return
     */
    default String getCallBackName(){
        return Thread.currentThread().getId() + ":" +this.getClass().getName();
    }
}
