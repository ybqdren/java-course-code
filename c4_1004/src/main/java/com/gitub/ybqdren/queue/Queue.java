package com.gitub.ybqdren.queue;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/11/7
 */
public interface Queue<E> {
    int getSize();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E getFront();
}
