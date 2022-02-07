package com.github.ybqdren;

/**
 * <h1> 线程创建方法一：继承 Thread 类创建线程类 </h1>
 * @Author zhao wen
 * @Version 0.0.1
 * @Date 2022/2/7
 **/
public class CreateDemo_1 {
    public static final int MAX_TURN = 5;
    public static String getCurThreadName(){
        return Thread.currentThread().getName();
    }

    // 线程的编号
    static int threadNo = 1;

    static class DemoThread extends Thread{
        // 1
        public DemoThread(){
            // 2
            super("DemoThread-" + threadNo++);
        }

        public void run(){
            // 3
            for(int i = 1; i < MAX_TURN ; i++){
                System.out.println(getName() + ", 轮次：" + i);
            }

            System.out.println(getName() + "运行结束.");
        }
    }

    public static void main(String[] args) {
        Thread thread = null;

        // 方法一：使用 Thread 子类创建和启动线程
        for(int i=0 ; i<2 ; i++){
            thread = new DemoThread();
            thread.start();
        }
        System.out.println(getCurThreadName() + " 运行结束.");
    }
}
