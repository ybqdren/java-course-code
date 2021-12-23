package com.gitub.ybqdren.queue;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/11/21
 */
public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front,tail; // front：队首位置   tail：队尾位置
    private int size; // 课下实现

    public LoopQueue(int capacity){
        data = (E[])new Object[capacity+1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        return data.length - 1; // 循环队列需要浪费一个capacity
    }

    @Override
    public boolean isEmpty(){
        return front == tail;
    }


    @Override
    public int getSize(){
        return size;
    }

    @Override
    public void enqueue(E e){
        if((tail+1) % data.length == front){
            resize(getCapacity() * 2); // 扩容 data.length 和capacity
            // 有一个1的差距，因为循环队列会浪费1个空间
        }

        data[tail] = e; // 将新入队的元素放到队列的tail位置
        tail = (tail+1) % data.length; // tail往后移动一位
        size++; // 维护size

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
        LoopQueue<Integer> queue = new LoopQueue<>();
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