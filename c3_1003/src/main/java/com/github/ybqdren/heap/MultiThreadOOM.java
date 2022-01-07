package com.github.ybqdren.heap;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/7 17:01
 * @package com.github.ybqdren.heap
 * @description  过多线程导致 OOM
 *
 * 由于每一个线程的开启都要占用系统内存，因此当线程数量太多时，也有可能导致 OOM。
 * 由于线程的栈空间也是在堆外分配的，因此和直接内存非常相似，如果想让系统支持更多的线程，那么应该使用一个较小的堆控件。
 *
 * -Xmxlg
 *
 * 书中创建了 1500 个线程报了一个 oom error，不过本机环境中没有测出来
 *
 * 如果抛出了 OOM，并且打出了 "unable to create new native thread"，表示系统创建线程的数量已经饱和，其原因是 Java 进程已经达到了可使用
 * 的内存上限。
 *
 * 要解决这类 OOM 的思路，除了合理的减少线程总数外，减少最大堆空间、减少线程的栈空间也是可行的。
 *
 * 要解决这个问题，也可以从以下两方面下手
 *
 * 1. -Xmx512m
 * 使用 512 MB 堆空间后，操作系统可以预留更多内存用于线程创建，因此程序可以正常执行
 *
 * 2. -Xmxlog -Xss128k
 * 减少每一个线程所占的内存空间，使用 -Xss 参数可以执行线程的栈空间
 *
 * 这里依然使用 1GB 的堆控件，但是将线程的栈空间减少到 128 KB,剩余可用的内存理应可以容纳更多的线程，因此程序也可以正常执行
 *
 * 注：如果减少了线程的栈空间大小，那么栈溢出的风险会响应的上升
 *
 *
 **/
public class MultiThreadOOM {

    public static class SleepThread implements Runnable{

        @Override
        public void run() {
            try {
               Thread.sleep(10000000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for(int i=0; i<50000 ; i++){
            new Thread(new SleepThread(),"Thread" + i).start();
            System.out.println("Thread" + i + " created");
        }
    }
}
