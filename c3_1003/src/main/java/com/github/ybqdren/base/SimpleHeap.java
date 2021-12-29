package com.github.ybqdren.base;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/26 16:23
 * @package com.github.ybqdren.base
 * @description 展示 Java 堆、方法区和 Java 栈之间的关系
 **/

// 声明一个 SimpleHeap 类
public class SimpleHeap {
    private int id;

    public SimpleHeap(int id) {
        this.id = id;
    }

    public void show(){
        System.out.println("My ID is " + id);
    }

    // 创建两个 SimpleHeap 实例，SimpleHeap 实例本身分配在堆中
    public static void main(String[] args) {
        SimpleHeap s1 = new SimpleHeap(1);
        SimpleHeap s2 = new SimpleHeap(2);
        s1.show();
        s2.show();
    }
}
