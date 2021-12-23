package com.github.ybqdren.cg;

import java.util.HashMap;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description  示例 4-7 垃圾回收时的停顿现象
 *
 * 停顿产生时，整个应用程序会被卡死，没有任何响应，因此这个停顿也叫做 Stop-The-World （STW）
 **/
public class StopTheWorld {

    public static class MyThread extends Thread{
        HashMap map = new HashMap();

        @Override
        public void run() {
            try {
                while(true){
                    if(map.size() * 512/1024/1024 >= 900){ // 当内存消耗大于 900 MB 时，清空内存，防止内存溢出
                        map.clear();
                        System.out.println("clean map");
                    }

                    byte[] b1;
                    for(int i=0 ; i<100 ;i++){
                        b1 = new byte[512];
                        map.put(System.nanoTime(),b1);
                    }
                    Thread.sleep(1);
                }
            }catch (Exception e){

            }
        }
    }

    public static class PrintThread extends Thread{
        public static final long startTime = System.currentTimeMillis();

        @Override
        public void run() {
            try {
                while (true){
                    long t = System.currentTimeMillis() - startTime;
                    System.out.println(t);
                }
            }catch (Exception e){

            }
        }
    }

    public static void main(String[] args) {

        // 开启两个线程

        // MyThread 则不停的消耗内存资源，以引起GC
        MyThread t = new MyThread();

        // PrintThread 负责每 0.1s 在控制台上输出时间戳
        PrintThread p = new PrintThread();


        t.start();
        p.start();

    }
}
