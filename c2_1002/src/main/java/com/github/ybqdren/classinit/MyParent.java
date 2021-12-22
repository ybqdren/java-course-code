package com.github.ybqdren.classinit;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/12/19
 */
public class MyParent {
    public static String parentStr = "now in MyParent";

    static {
        System.out.println("my parent class init;");
    }
}
