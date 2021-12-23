package com.gitub.ybqdren.linkedlist;


import com.gitub.ybqdren.stack.Stack;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description
 **/
public class LinkedList_stack<E> implements Stack<E> {

    private LinkedList_dummyHead<E> list;

    public LinkedList_stack(){
        list = new LinkedList_dummyHead<E>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.removeLast();
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(list);
        return res.toString();
    }
}
