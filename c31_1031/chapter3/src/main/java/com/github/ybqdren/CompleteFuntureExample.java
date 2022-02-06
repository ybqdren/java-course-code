package com.github.ybqdren;

import java.util.concurrent.*;

/**
 * <h1> 显式设置 CompletableFuture </h1>
 * @author zhao wen
 * @since 0.0.1
 * <p>
 *     ● 由上述代码可知，代码0创建了一个线程池，代码1创建了一个CompletableFuture对象，代码2将提交任务到异步线程池中执行。
 *
 *     ● 代码3调用future的get()方法企图获取future的结果，如果future的结果没有被设置，则调用线程会被阻塞。
 *
 *     ● 在代码2创建的任务内，代码2.1表示休眠3s，模拟异步任务的执行，代码2.2则表示在休眠3s后，调用future的complete方法设置future的结果，设置完结果后，所有由于调用future的get()方法而被阻塞的线程会被激活，并返回设置的结果。
 * </p>
 **/
public class CompleteFuntureExample {
    /** 0 自定义线程池 */
    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private final static ThreadPoolExecutor POOL_EXECUTOR =
            new ThreadPoolExecutor(AVALIABLE_PROCESSORS * 2 ,
                    1024 ,
                    2000,
                    TimeUnit.MINUTES ,
                    new LinkedBlockingQueue<>(5) ,
                    new ThreadPoolExecutor.CallerRunsPolicy());


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1. 创建一个 CompletableFuture 对象
        CompletableFuture<String> future = new CompletableFuture<String>();

        // 2. 开启线程计算任务结果，并设置
        POOL_EXECUTOR.execute(() -> {
            // 2.1 休眠 3s ，模拟任务计算
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 2.2 设置计算结果到 future
            System.out.println("----" + Thread.currentThread().getName() + " set future result ----");

            future.complete("hello , ybqdren");
        });


        // 3. 等待计算结果
        System.out.println("---- main thread wait future result ----");
        System.out.println(future.get());
        System.out.println("---- main thread wait future result ----");
    }
}
