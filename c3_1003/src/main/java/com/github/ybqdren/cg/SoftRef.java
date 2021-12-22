package com.github.ybqdren.cg;

import java.lang.ref.SoftReference;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description 示例 4-3 软引用  p72~p73
 * 软引用-可被回收的引用
 **/
public class SoftRef {
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

    public static void main(String[] args) {
        User u = new User(1,"geym"); // 建立了 User 类的实例，这里的 u 为强引用
        SoftReference<User> userSoftRef = new SoftReference<User>(u); // 通过强引用 u 建立软引用
        u = null; // 去除强引用

        System.out.println(userSoftRef.get());  // 从软引用中重新获得强引用对象
        System.gc(); // 再一次进行垃圾回收
        System.out.println("After  GC: ");
        System.out.println(userSoftRef.get());  // 在垃圾回收之后，再次获得软引用中的对象

        byte[] b = new byte[1024*925*7]; // 分配一块较大的内存，让系统认为内存资源紧张
        System.gc(); // 进行以此垃圾回收（实际上，这个是多余的，因为在分配大数据时，系统会自动进行 GC ，这里显式调用只是为了更清楚说明问题）
        System.out.println(userSoftRef); // 再次从软引用中获取数据
    }
}
