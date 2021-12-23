package com.gitub.ybqdren.queue;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description
 **/
public class Deque<E> implements Queue<E> {
    private E[] data;
    private int front,tail;
    private int size; // 方便起见，我们的Deque实现，将使用size记录deque中存储的元素数量

    public Deque(int capacity) {
        data = (E[]) new Object[capacity]; // 由于使用size，我们的Deque实现不浪费空间

        front = 0;
        tail = 0;
        size = 0;
    }

    public Deque() {
        this(10);
    }

    @Override
    public int getSize() {
        return size;
    }

    private int getCapacity(){
        return data.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {

    }

    @Override
    public E dequeue() {
        return null;
    }

    @Override
    public E getFront() {
        return null;
    }
}
