package com.gitub.ybqdren.stack;

import com.gitub.ybqdren.array.Array_m;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description
 * 栈：后进先出 先进后出（FOLI）
 **/
public class ArrayStack<E> implements Stack<E> {
    Array_m<E> array;

    public ArrayStack(int capacity) {
        this.array = new Array_m(capacity);
    }

    public ArrayStack() {
        this.array = new Array_m();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    // 查看栈的容积
    // 和栈是无关的，这是因为我们使用到了动态数组构建栈，因此才会有需要查看这个特性
    public int getCapcity(){
        return array.getCapacity();
    }


    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for(int i=0;i<array.getSize();i++){
            res.append(array.get(i));
            // 判断是否不为最后一个元素
            if(i != array.getSize() - 1){
                res.append(", ");
            }
            res.append("] top");
        }
        return res.toString();
    }
}
