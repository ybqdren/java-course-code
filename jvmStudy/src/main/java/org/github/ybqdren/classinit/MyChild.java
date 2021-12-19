package org.github.ybqdren.classinit;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/12/19
 */
public class MyChild extends MyParent implements API{
    static {
        System.out.println("My child class init;");
    }

    static {
        System.out.println("my child static block 222");
    }

    private static  int a = 5;

    static {
        System.out.println("my child static block 333"+ a);
    }


    @Override
    public void t1() {
        System.out.println("now in mychild t1()");
    }
}
