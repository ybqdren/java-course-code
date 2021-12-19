package org.github.ybqdren.classinit;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/12/19
 */

// 接口中无法定义静态代码块
public interface API {
    public static String str = "now in api";

    public void t1();

    public default void t3(){
        System.out.println("now in api in t3()");
    }

}
