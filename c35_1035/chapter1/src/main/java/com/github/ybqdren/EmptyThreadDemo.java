package com.github.ybqdren;

/**
 * <h1> 创建一个空线程 </h1>
 * @author zhao wen
 * @since 0.0.1
 **/
public class EmptyThreadDemo {
    public static void main(String[] args) {
        // 使用 Thread 类创建和启动线程
        Thread thread = new Thread();
        System.out.println("当前线程名称：" + thread.getName());
        System.out.println("当前线程ID：" + thread.getId());
        System.out.println("当前线程状态：" + thread.getState());
        System.out.println("当前线程优先级：" + thread.getPriority());
        System.out.println(thread.getName() + "运行结束");
        thread.start();
    }
}
