package com.github.ybqdren.cg;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description
 * 每一个软引用都可以附带一个引用队列，当对象的可达性状态发生改变时（由可达变为不可达），
 * 软引用对象都会进入引用队列。通过这个引用队列，可以跟踪对象的回收情况。
 *
 * 软引用和弱引用都非常适合来报错哪些可有可无的缓存数据。如果这么做，当系统内存不足时，这些缓存数据会被回收，就不会导致内存溢出。
 * 而当内存资源充足时，这些缓存数据又可以存在相当长的时间，从而起到加速系统的作用。
 **/

public class SoftRefQ {
    public static class User{
        public int id;
        public String name;

        public User(int id,String name){
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    static ReferenceQueue<User> softQueue = null;

    public static class CheckRefQueue extends Thread{
        @Override
        public void run() {

            // --- 跟踪引用队列，打印对象的回收情况
            while (true){
                if(softQueue != null){
                    UserSoftReference obj = null;

                    try{
                        obj = (UserSoftReference) softQueue.remove();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(obj != null){
                        System.out.println("user id "+obj.uid+" id delete");
                    }
                }
            }
            // ----

        }
    }

    // 实现一个软引用类，扩展软引用的目的是记录 User.uid，后续再引用队列中，就可以通过这个 uid 字段知道哪个 User 实例被回收了
    public static class UserSoftReference extends SoftReference<User>{
        int uid;

        public UserSoftReference(User referent, ReferenceQueue<? super User> q) {
            super(referent, q);
            this.uid = referent.id;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new CheckRefQueue();
        t.setDaemon(true);
        t.start();
        User u = new User(1,"geym");
        softQueue = new ReferenceQueue<User>();

        // 在创建软引用时，指定了一个软引用队列，当给定的对象实例被回收时，就会被加入这个引用队列，通过访问该队列可以跟踪对象的回收情况
        UserSoftReference userSoftRef = new UserSoftReference(u,softQueue);

        u = null;
        System.out.println(userSoftRef.get());
        System.gc();
        // 内存足够，不会被回收
        System.out.println("After GC");
        System.out.println(userSoftRef.get());

        System.out.println("try to create byte array and GC ");
        byte[] b = new byte[1024*925*7];
        System.gc();
        System.out.println(userSoftRef.get());

        Thread.sleep(1000);
    }
}
