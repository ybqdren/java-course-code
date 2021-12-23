package com.gitub.ybqdren.linkedlist;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description 使用虚拟头结点来替换原来的head位置
 **/
public class LinkedList_dummyHead<E> {
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

    private Node dummyHead; // 使用 dummyHead 替换 head
    private int size;

    public LinkedList_dummyHead(){
        dummyHead = new Node(null,null);  // 虚拟头结点为一个空的Node实例对象
        size = 0;
    }

    // 获取链表中元素的个数
    public int getSize(){ return size; }

    // 返回链表是否为空
    public boolean isEmpty(){ return size == 0; }

    // 在链表的index(0-based)位置添加新的元素e
    // index在链表中不是一个常用的操作,因为我们使用链表就通常不会使用索引来获取元素，练习用：）
    public void add(int index,E e){
        if(index < 0 || index > size ){ throw  new IllegalArgumentException("Add faild.Illegal index."); }
        // 用更优雅的dummyhead方式替换上面的代码
        Node prev = dummyHead;  // 链表是从dummyHead 虚拟头结点开始的 ，也即从链表之前的哪个虚拟头结点开始遍历链表

        // 只需要遍历[0,index) 之间的元素
        for(int i =0; i < index-1 ; i++){ prev = prev.next; }

        prev.next = new Node(e,prev);
        size ++;
    }

    // 在链表头添加新的元素e
    public void addFirst(E e){
        add(0,e); // 直接在0这个位置添加元素就好了
    }

    // 向链表的末尾添加新的元素，直接在index=size 这个位置插入指定的元素
    public void addLast(E e){
        add(size,e);
    }

    // 获得链表的第index(0-based)个位置的元素
    // 在链表中不是一个常用的操作，练习用
    public E get(int index){
        if(index < 0 || index > size ){ throw  new IllegalArgumentException("Get faild.Illegal index."); }

        // 遍历链表是要遍历链表的所有元素 ，即[0,size]
        Node cur = dummyHead.next; // 从链表的第一个元素开始遍历
        for(int i=0 ; i<index ; i++){
            cur = cur.next;
        }

        return cur.e;
    }

    // 获取链表的第一个元素
    public E getFirst(){
        return  get(0);
    }

    // 获取链表中租后一个元素
    public E getLast(){
        return get(size - 1);
    }

    // 修改链表的第index(0-based)个位置的元素e
    // 在链表中不是一个常用的操作，练习用:)
    public void set(int index,E e){
        if(index < 0 || index > size ){ throw  new IllegalArgumentException("Set faild.Illegal index."); }

        Node cur = dummyHead.next;
        for(int i=0 ;i<index; i++){
            cur = cur.next;
        }

        cur.e = e;
    }

    // 查找链表中是否有元素e
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while (cur != null) {
            if(cur.e.equals(e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    // 从链表中删除index(0-based)位置的元素，返回删除的元素
    // 在链表中不是一个常用的操作，练习用:)
    public E remove(int index){
        if(index < 0 || index > size ){ throw  new IllegalArgumentException("Remove faild.Illegal index."); }

        Node prev = dummyHead;
        for(int i = 0 ; i < index ; i++){
            prev = prev.next;
        }

        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size-- ;

        return retNode.e;
    }

    // 从链表中删除第一个元素，返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    // 从链表中删除最后一个元素，返回删除的元素
    public E removeLast(){
        return remove(size-1);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while(cur != null){
            res.append(cur + "-->");
            cur = cur.next;
        }

        // 使用for循环来代替
/*        for(Node cur = dummyHead.next; cur != null ; cur = cur.next){
            res.append(cur+"-->");
        }*/

        res.append("Null");
        return res.toString();
    }
}
