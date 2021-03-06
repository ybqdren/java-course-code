package com.github.ybqdren;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * <h1> {@link  FutureTask} 测试</h1>
 * @author zhao wen
 * @since 0.0.1
 * <p>
 *     ● 在上述代码中，doSomethingA和doSomethingB方法都是有返回值的任务，main函数内代码1创建了一个异步任务futureTask，其内部执行任务doSomethingA。
 *
 *     ● 代码2则创建了一个线程，以futureTask为执行任务并启动；代码3使用main线程执行任务doSomethingB，这时候任务doSomethingB和doSomethingA是并发运行的，等main函数运行doSomethingB完毕后，执行代码4同步等待doSomethingA任务完成，然后代码5打印两个任务的执行结果。
 *
 *     ● 如上可知使用FutureTask可以获取到异步任务的结果。
 *
 * </p>
 **/
public class AsyncFutureExample {
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

        // 1. 创建 future 任务 - A 方法
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            String result = "";

            try {
                result = doSomethingA();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        });

        // 2. 开启异步单元执行任务 A
        Thread thread = new Thread(futureTask , "threadA");
        thread.start();

        // 3. 执行任务 B
        String taskBResult = doSomethingB();

        // 4. 同步等待线程 A 运行结束
        String taskAResult = futureTask.get();

        // 5. 打印两个任务执行结果
        System.out.println(taskAResult + " " + taskBResult);
        System.out.println(System.currentTimeMillis() - start);
    }
}
