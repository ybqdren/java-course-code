package com.github.ybqdren;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * <h1> 基于CompletableFuture实现异步计算与结果转换 </h1>
 * @author zhao wen
 * @since 0.0.1
 *
 * <p>
 *     异步执行一个任务，并且不需要任务的执行结果时可以使用该方法，比如异步打日志，异步做消息通知
 * </p>
 **/
public class CompleteFuntureRunAsyncExample {
    /** 0 自定义线程池 */
    private final static ThreadPoolExecutor BIG_POOL_EXECUTOR =
            new ThreadPoolExecutor(8,
                                    8 ,
                                    1,
                                    TimeUnit.MINUTES ,
                                    new LinkedBlockingQueue<>(10));

   /** runAsync方法不会有返回值 */
   public static void runAsyncWithBizExecutor() throws ExecutionException, InterruptedException {
       // 1.1 创建异步任务，并返回 future
       CompletableFuture future = CompletableFuture.runAsync(new Runnable() {
           @Override
           public void run() {
               // 1.1.1 休眠 2s 模拟计算
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println("over");
           }
       }, BIG_POOL_EXECUTOR);

       // 1.2 同步等待异步任务执行结束
       System.out.println(future.get());
   }

    /** 2. 有返回值的异步执行 */
    public static void supplyAsync() throws ExecutionException, InterruptedException {
        // 2.1 创建异步任务，并返回 future
        CompletableFuture future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                // 2.1.1 休眠 2s 模拟任务计算
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 2.1.2 返回异步计算结果
                return "hello, ybqdren";
            }
        });

        // 2.2 同步等待异步任务执行结束
        System.out.println(future.get());
    }
}
