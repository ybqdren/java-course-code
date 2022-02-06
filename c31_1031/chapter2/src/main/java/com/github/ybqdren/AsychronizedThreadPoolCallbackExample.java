package com.github.ybqdren;

import java.util.concurrent.*;

/**
 * <h1> 向线程池投递一个Callable类型的异步任务，并且获取其执行结果 </h1>
 * @author zhao wen
 * @since 0.0.1
 *<p>
 *     doSomethingA方法具有String类型的返回值，
 *     创建了一个线程池，在main方法中，使用lambda表达式将Callable类型的任务提交到线程池，
 *     提交后会马上返回一个Future对象，在futureA上调用get()方法阻塞等待异步任务的执行结果。
 *
 *</p>
 *
 * <p>
 *     此方式会导致 main 方法的阻塞
 * </p>
 **/
public class AsychronizedThreadPoolCallbackExample {
    // 0 自定义线程池
    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

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

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 1. 开启异步单元执行任务 A
        Future<? > result = POOL_EXECUTOR.submit(() -> doSomethingA());

        // 3. 同步等待执行结果
        System.out.println(result.get());
    }
}
