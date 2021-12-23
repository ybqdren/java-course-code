package com.gitub.ybqdren.stack;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description
 **/
public interface Stack<E> {
    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
