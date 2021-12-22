package com.github.ybqdren.classinit;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/12/19
 */
public class MyChild extends MyParent implements API{

    public final static String childStr = "now in MyChild";

    static {
        System.out.println("My child class init;");
    }

    static {
        System.out.println("my child static block 222");
    }

    public static  int a = 5;   // 开放出去

    static {
        System.out.println("my child static block 333"+ a);
    }


    public static void t2(){
        System.out.println("now in mychild t2()");
    }

    @Override
    public void t1() {
        System.out.println("now in mychild t1()");
    }
}
