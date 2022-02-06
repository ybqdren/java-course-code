package com.github.ybqdren;

import java.util.concurrent.*;

/**
 * <h1> 将 FutureTask 提交到线程池来执行 </h1>
 * @author zhao wen
 * @since 0.0.1
 * <p>
 *   代码1调用了线程池的submit方法提交了一个任务到线程池，然后返回了一个 futureTask 对象。
 * </p>
 *
 **/
public class AsyncFutureThreadPool2Example {
    /** 0 自定义线程池 */
    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private final static ThreadPoolExecutor POOL_EXECUTOR =
            new ThreadPoolExecutor(AVALIABLE_PROCESSORS * 2 ,
                                                1024 ,
                                                2000,
                                                TimeUnit.MINUTES ,
                                                new LinkedBlockingQueue<>(5) ,
                                                new ThreadPoolExecutor.CallerRunsPolicy());

    public static String doSomethingA(){
        try{
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("--- doSomethingA ---");

        return "TaskAResult";
    }

    public static String doSomethingB(){
        try{
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("--- doSomethingB ---");

        return "TaskBResult";
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();

        // 1. 开启异步单元执行任务 A
        Future<String> future =POOL_EXECUTOR.submit(() -> {
            String result = "";

            try {
                result = doSomethingA();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        });

        // 2. 执行任务 B
        String taskBResult = doSomethingB();

        // 3. 同步等待线程 A 运行结束
        String taskAResult = future.get();

        // 4.打印两个任务执行结果
        System.out.println(taskAResult + " " + taskBResult);
        System.out.println(System.currentTimeMillis() - start);
    }
}
