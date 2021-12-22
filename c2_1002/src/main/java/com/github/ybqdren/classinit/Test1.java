package com.github.ybqdren.classinit;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/12/19
 */
public class Test1 {
    static {
        System.out.println("now Test1 class init");
    }

    public static void main(String[] args) throws ClassNotFoundException {
//        MyChild myChild = new MyChild();

//        System.out.println("myChild.str=="+myChild.str);

/*        MyClassLoader myClassLoader = new MyClassLoader("MyClassloader1");
        Class cls1 = myClassLoader.loadClass("org.github.ybqdren.classinit.MyChild");*/

//        System.out.println("myChild.a=="+MyChild.a);

//        MyChild.t2();

//        Class cls = Class.forName("org.github.ybqdren.classinit.MyChild");

/*        MyClassA myClassA = MyClassA.getInstance();
        System.out.println("MyClassA.a =="+myClassA.getA());
        System.out.println("MyClassA.b =="+myClassA.getB());*/

//        System.out.println("MyChild.parenstr=="+MyChild.str);

//        MyChild[] mcs = new MyChild[2];

        System.out.println("MyChild.childStr=="+MyChild.childStr);

        System.out.println("over =======");
    }
}
