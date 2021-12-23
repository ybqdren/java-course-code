package com.gitub.ybqdren.linkedlist;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description
 **/
public class LinkedList<E> {
    // 对于用户来说，并不需要了解链表的底层实现是什么，所以使用private 对外部的用户屏蔽类中的细节
    private class Node{
        public E e;
        private Node next;

        public Node(E e,Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){ this(e,null); }

        public Node(){ this(null,null); }

        @Override
        public String toString(){ return e.toString(); }
    }

    private Node head;
    private int size;

    public LinkedList(){
        head = null;
        size = 0;
    }

    // 获取链表中元素的个数
    public int getSize(){ return size; }

    // 返回链表是否为空
    public boolean isEmpty(){ return size == 0; }

    // 在链表头添加新的元素e
    public void addFirst(E e){
        /*Node node = new Node(e);
        node.next = head;
        head = node;
        */

        // 用递归替换上面的操作
        head = new Node(e,head); // 即将e放到当前head的位置上，并修改其next指针指向head，这样就将e插入链表，并将链表有串起来了
        size++;
    }

    // 在链表的index(0-based)位置添加新的元素e
    // index在链表中不是一个常用的操作,因为我们使用链表就通常不会使用索引来获取元素，练习用：）
    public void add(int index,E e){
        if(index < 0 || index > size ){ throw  new IllegalArgumentException("Add faild.Illegal index."); }

        // 1.如果当前链表中为空，就直接插入到链表的头部
        if(index == 0){
            addFirst(e);

        // 2.如果当前链表不为空，就通过index来找到链表中指定的位置，来进行插入
        }else{
            Node prev = head;
            for(int i =0; i < index-1 ; i++){
                prev = prev.next;
            }

            // 改变前后指针的引用，将当前节点插入链表中
            /*Node node = new Node(e);
            node.next = prev.next;
            prev.next = node;
*/
            // 此处同样可以使用递归来替换上面的操作
            prev.next = new Node(e,prev);

            size ++;
        }
    }

    // 向链表的末尾添加新的元素，直接在index=size 这个位置插入指定的元素
    public void addLast(E e){
        add(size,e);
    }
}
