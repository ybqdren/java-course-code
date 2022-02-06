package com.github.ybqdren;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/27 11:56
 * @package com.github.ybqdren.chapter2
 * @description
 * <h1> 异步编程模型 </h1>
 * <p>
 *     Java 中有两种方式来显式开启一个线程进行异步处理：
 *      1. 实现 java.lang.Runnable 接口的 run 方法
 *      2. 实现 Thread 类，并重写 run 方法
 * </p>
 *
 * <p>
 *     我们在main函数所在线程内首先使用lambda表达式创建了一个java.lang.Runnable接口的匿名实现类，
 *     用来异步执行doSomethingA任务，然后将其作为Thread的参数并启动。这时候线程A与main线程并发运行，
 *     也就是任务doSomethingA与任务doSomethingB并发运行，
 *     等main线程运行完doSomethingB任务后同步等待线程A运行完毕。
 *     运行上面代码，这时整个过程耗时大概2s，可知使用异步编程可以大大缩短任务运行时间。
 * </p>
 **/
public class AsychronizedExampleThreadRun {
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

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        // 1. 开启异步单元执行任务 A
        Thread thread = new Thread(() -> {
            try {
                doSomethingA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "threadA");

        thread.start();

        // 2. 执行任务 B
        doSomethingB();

        // 3. 同步等待线程 A 运行结束
        thread.join();
        System.out.println(System.currentTimeMillis() - start);
    }
}
