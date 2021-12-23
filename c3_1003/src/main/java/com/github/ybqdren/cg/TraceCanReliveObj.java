package com.github.ybqdren.cg;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description 示例 4-6 使用虚引用跟踪一个可复活对象的回收
 * 虚引用：存放一些资源释放操作
 **/
public class TraceCanReliveObj {
    // TraceCanReliveObj 对象是一个在 finalize() 函数中可复活的对象
    public static TraceCanReliveObj obj;

    static ReferenceQueue<TraceCanReliveObj> phantomQueue = null;

    public static class CheckRefQueue extends Thread{
        @Override
        public void run() {
            while (true){
                if(phantomQueue != null){
                    PhantomReference<TraceCanReliveObj> obj = null;

                    try{
                        obj = (PhantomReference<TraceCanReliveObj>) phantomQueue.remove();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(obj != null){
                        System.out.println("TraceCanReliveObj is delete by GC");
                    }
                }
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("CanReliveObj finalize called");
        obj=this;
    }

    @Override
    public String toString() {
        return "I am CanReliveObj";
    }


    public static void main(String[] args) throws InterruptedException {
        Thread t = new CheckRefQueue();
        t.setDaemon(true);
        t.start();

        phantomQueue = new ReferenceQueue<TraceCanReliveObj>();
        obj = new TraceCanReliveObj();

        // 构造了 TraceCanReliveObj 对象的虚引用，并指定了引用队列
        PhantomReference<TraceCanReliveObj> phantomRef = new PhantomReference<>(obj,phantomQueue);

        // 将强引用去除
        obj = null;

        // 第一次进行 GC，但是由于对象可复活，GC 无法回收该对象
        System.gc();
        Thread.sleep(1000);
        if(obj == null){
            System.out.println("obj 是 null");
        }else{
            System.out.println("obj 可用");
        }

        System.out.println("第 2 次 gc");
        obj=null;

        // 进行第二次 GC ，由于 finalize() 只会被调用一次，因此第二次 GC 会回收对象，同时其引用队列应该也会捕获到对象的回收
        System.gc();
        Thread.sleep(1000);
        if(obj == null){
            System.out.println("obj 是 null");
        }else{
            System.out.println("obj 可用");
        }
    }
}
