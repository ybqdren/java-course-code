package com.github.ybqdren.escape;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/11/6
 * <h1>自动拆箱引发的空指针问题</h1>
 */

@SuppressWarnings("all")
public class UnboxingNpe {
    private static int add(int x,int y){
        return x+y;
    }

    private static boolean compare(long x,long y){
        return x>= y;
    }
    public static void main(String[] args) {

        //1. 变量赋值自动拆箱出现的空指针
        // javac 编译 UnboxingNpe 编译java为字节码文件（.class文件）
        //      如果上面在执行时报错：UnboxingNpe.java:30: 错误: 编码 GBK 的不可映射字符 (0xAF)
        //         则使用 javac -encoding UTF-8 UnboxingNpe.java
        //             即使用utf-8的编码方式编译.java文件
        // javap -c UnboxingNpe  java虚拟机自带的反解析工具 根据java字节码文件
        //                               反解析出java的汇编指令 本地变量表...
        Long count = null;
        long count_ =count;

        //2. 方法传参时自动拆箱引发的空指针问题
/*        Integer left = null;
        Integer right = null;
        System.out.println(add(left,right));*/

        //3. 用于大小比较的场景
/*        Long left = 10L;
        Long right = null;
        System.out.println(compare(left,right));*/
    }
}
