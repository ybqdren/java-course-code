package com.gitub.ybqdren.queue;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description 不浪费1个空间
 **/
public class LoopQueue_2<E> implements Queue<E>{
    private E[] data;
    private int front,tail;
    private int size;

    public LoopQueue_2(int capacity) {
        data = (E[])new Object[capacity]; // 由于不浪费空间，所以data静态数组的大小是capacity
                                            // 而不是capactity+1

        this.front = 0;
        this.tail = 0;
        this.size = 0;
    }

    public LoopQueue_2() {
        this(10);
    }

    @Override
    public boolean isEmpty() {
        // 注意，我们不再使用front和tail之间的关系来判断队列是否为空，而直接使用size
        return size == 0;
    }

    public int getCapacity(){
        return data.length;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void enqueue(E e) {

        // 注意，我们不再使用front和tail之间的关系来判断队列是否为满，而直接使用size
        if(size == getCapacity()){
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;

    }

    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity + 1]; // 因为数组要浪费1个空间，所以数组容量要加1
        for(int i=0 ;i < size ;i++){
            // 将data中的元素放到newData中去
            newData[i] = data[(i+front) % data.length]; // data中的值相对于newData中的值，是存在偏移的

            data = newData;
            front = 0;
            tail = size;
        }
    }



    @Override
    public E dequeue(){
        if(isEmpty()) { throw  new IllegalArgumentException("Cannot dequeue from an empty queue."); }

        E ret = data[front]; // 将出队的元素保存返回
        data[front] = null; // 将出队的位置设置为null
        front = (front+1) % data.length; // front向后移动一位
        size --; // 维护size

        // 缩容：等队列中的元素个数 == 队列容量的1/4时
        if(size == getCapacity()/4 && getCapacity()/2 != 0){
            resize(getCapacity()/2);
        }

        return ret;
    }

    @Override
    public E getFront(){
        if(isEmpty()){
            throw new IllegalArgumentException("Queue is empty");
        }

        return data[front];
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d,capacity = %d \n",size,getCapacity()));
        res.append("front [");
        for(int i = front ; i != tail ; i = (i+1) % data.length){
            res.append(data[i]);
            if((i+1) % data.length != tail){ // 如果当前元素不是队列中最后一个元素
                res.append(",");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        LoopQueue_2 queue = new LoopQueue_2();
        for(int i=0 ; i<10 ; i++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

}
