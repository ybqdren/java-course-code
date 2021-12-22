package com.github.ybqdren.cg;

import java.lang.ref.WeakReference;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description 示例 4-5 弱引用的特点
 * 弱引用和软引用一样，在构造弱引用时，也可以制定一个引用队列，当弱引用对象被回收时，就会加入指定的引用队列，通过这个队列
 * 可以跟踪对象的回收情况
 **/
public class WeakRef {
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
        User u = new User(1,"geym");

        // 构造了弱引用
        WeakReference<User> userWeakRef = new WeakReference<User>(u);
        u = null; // 去除了强引用
        System.out.println(userWeakRef.get());
        System.gc(); // 进行了 GC

        // 不管当前内存空间足够与否，都会回收它的内存
        System.out.println("After GC: ");

        // 重新尝试从弱引用中获取对象
        System.out.println(userWeakRef.get());
    }
}
