package com.gitub.ybqdren.linkedlist;


import com.gitub.ybqdren.queue.Queue;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description
 *
 * 尾节点链表
 **/
public class LinkedListQueue<E> implements Queue {
    private class Node{
        public E e;
        private Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){ this(e,null); }

        public Node(){ this(null,null); }

        @Override
        public String toString(){ return e.toString(); }
    }

    private Node head,tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 带有入队和出队操作的尾节点操作
    @Override
    public void enqueue(Object o) {
        // 判断链表尾是否为空
        if(tail == null){
            tail = new Node((E) o);
            head = tail;
        }else{
            tail.next = new Node((E) o);
            tail = tail.next;
        }

        size ++;
    }

    @Override
    public Object dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        Node retNode = head;
        head = head.next;
        retNode.next = null;

        // 如果链表中只有一个元素 head == tail
        if(head == tail){
            tail = null;
        }

        size --;
        return retNode;
    }

    @Override
    public Object getFront() {
        if(isEmpty()){
            throw  new IllegalArgumentException("Queue is empty");
        }

        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front");

        Node cur = head;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }

        res.append("Null tail");
        return res.toString();
    }

}
