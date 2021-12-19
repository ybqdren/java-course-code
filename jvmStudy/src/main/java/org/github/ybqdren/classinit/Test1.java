package org.github.ybqdren.classinit;

import org.github.ybqdren.classloader.MyClassLoader;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/12/19
 */
public class Test1 {
    public static void main(String[] args) throws ClassNotFoundException {
        MyChild myChild = new MyChild();

//        System.out.println("myChild.str=="+myChild.str);

        MyClassLoader myClassLoader = new MyClassLoader("MyClassloader1");
        Class cls1 = myClassLoader.loadClass("org.github.ybqdren.classinit.MyChild");
        System.out.println("over =======");
    }
}
