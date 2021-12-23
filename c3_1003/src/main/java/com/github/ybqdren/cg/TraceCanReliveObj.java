package com.github.ybqdren.cg;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description 示例 4-6 使用虚引用跟踪一个可复活对象的回收
 **/
public class TraceCanReliveObj {
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
        PhantomReference<TraceCanReliveObj> phantomRef = new PhantomReference<>(obj,phantomQueue);

        obj = null;
        System.gc();
        Thread.sleep(1000);
        if(obj == null){
            System.out.println("obj 是 null");
        }else{
            System.out.println("obj 可用");
        }

        System.out.println("第 2 次 gc");
        obj=null;
        System.gc();
        Thread.sleep(1000);
        if(obj == null){
            System.out.println("obj 是 null");
        }else{
            System.out.println("obj 可用");
        }
    }
}
