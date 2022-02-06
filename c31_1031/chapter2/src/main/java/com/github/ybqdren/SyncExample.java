package com.github.ybqdren;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/27 11:37
 * @package com.github.ybqdren.chapter2
 * @description
 * <h1> 同步编程模型：使用 Thread 来创建一个线程来进行异步执行 </h1>
 * <p> 启动 1 个 Java 虚拟机，进程内会创建一个用户线程来执行 main 函数（1 个 main 线程）</p>
 * <p> main 线程内首先执行了 doSomethingA 方法，然后执行了 doSomethingB 方法，这两个方法是顺序执行的</p>
 * <p> System::out --- doSomethingA ------ doSomethingB ---4036</p>
 **/
public class SyncExample {
    public static void doSomethingA(){
        try{
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("--- doSomethingA ---");
    }

    public static void doSomethingB(){
        try{
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("--- doSomethingB ---");
    }

    public static void main(String[] args){
        long start = System.currentTimeMillis();

         // 1. 执行任务 A
        doSomethingA();

        // 2. 执行任务 B
        doSomethingB();

        System.out.println(System.currentTimeMillis() - start);
    }
}
