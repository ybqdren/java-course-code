package com.github.ybqdren;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zhao wen
 * @since 0.0.1
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

        // 1. 创建 future 任务
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
