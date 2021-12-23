package com.github.ybqdren.lock;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description 示例 8-2 线程不安全示例：将 ArrayList 在多线程下使用
 * 解决 ArrayList 线程不安全的问题的最好办法就是使用 Vector 进行替代，因为 Vector 的 add() 方法使用到了 synchronized 关键字
 *
 * synchronized 关键字保证了每次只有一个线程可以访问对象实例，确保了多线程环境中对象内部数据的一致性
 *
 * 由于 ArrayList 不是线程安全的 ，所以很有可能抛出以下错误：
 * Exception in thread "Thread-1" java.lang.ArrayIndexOutOfBoundsException: Index 240097 out of bounds for length 106710
 * 	at java.base/java.util.ArrayList.add(ArrayList.java:455)
 * 	at java.base/java.util.ArrayList.add(ArrayList.java:467)
 * 	at com.github.ybqdren.lock.ThreadUnSafe$AddToList.run(ThreadUnSafe.java:26)
 * 	at java.base/java.lang.Thread.run(Thread.java:833)
 **/
public class ThreadUnSafe {

    public static List<Integer> numberList = new ArrayList<>();

    public static class AddToList implements Runnable{
        int startNum = 0;

        public AddToList(int startNum) {
            this.startNum = startNum;
        }

        @Override
        public void run() {
             int count = 0;
             while(count < 1000000){
                 numberList.add(startNum);
                 startNum += 2;
                 count++;
             }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new AddToList(0));
        Thread t2 = new Thread(new AddToList(1));
        t1.start();
        t2.start();
    }
}
