package com.github.ybqdren;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <h1> 线程池实现异步编程 </h1>
 * @author zhao wen
 * @since 0.0.1
 * <p>
 *     创建了一个线程池，这里我们设置线程池核心线程个数为当前物理机的CPU核数，最大线程个数为当前物理机CPU核数的2倍；
 *     设置线程池中最大的线程数量为 1024
 *     设置线程池阻塞队列的大小为5；
 *     需要注意的是，我们将线程池的拒绝策略设置为CallerRunsPolicy，
 *     即当线程池任务饱和，执行拒绝策略时不会丢弃新的任务，而是会使用调用线程来执行；
 *     另外我们使用了命名的线程创建工厂，以便排查问题时可以方便追溯是哪个相关业务。
 *
 *     创建完线程池后，代码1则把异步任务提交到了线程池内运行，而不是直接开启一个新线程来运行；
 *     这里使用线程池起到了复用线程的作用，避免了线程的频繁创建与销毁，另外对线程个数也起到了限制作用。
 * </p>
 *
 **/
public class AsychronizedThreadPoolExample {
    // 0 自定义线程池
    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    /*
        corePoolSize:指定了线程池中的线程数量，它的数量决定了添加的任务是开辟新的线程去执行，还是放到workQueue任务队列中去；

        maximumPoolSize:指定了线程池中的最大线程数量，这个参数会根据你使用的workQueue任务队列的类型，决定线程池会开辟的最大线程数量；

        keepAliveTime:当线程池中空闲线程数量超过corePoolSize时，多余的线程会在多长时间内被销毁；

        unit:keepAliveTime的单位

        workQueue:任务队列，被添加到线程池中，但尚未被执行的任务；它一般分为直接提交队列、有界任务队列、无界任务队列、优先任务队列几种；

        threadFactory:线程工厂，用于创建线程，一般用默认即可；

        handler:拒绝策略；当任务太多来不及处理时，如何拒绝任务；

        参考：https://www.cnblogs.com/dafanjoy/p/9729358.html
     */
    private final static ThreadPoolExecutor POOL_EXECUTOR =
            new ThreadPoolExecutor(AVALIABLE_PROCESSORS * 2 ,
                                                1024 ,
                                                2000,
                                                TimeUnit.MINUTES ,
                                                new LinkedBlockingQueue<>(5) ,
                                                new ThreadPoolExecutor.CallerRunsPolicy());

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
        POOL_EXECUTOR.execute(() -> {
            try {
                doSomethingA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 2. 执行任务 B
        doSomethingB();

        // 3. 同步等待线程 A 运行结束
        System.out.println(System.currentTimeMillis() - start);

        // 4. 挂起当前线程
        Thread.currentThread().join();
    }
}
