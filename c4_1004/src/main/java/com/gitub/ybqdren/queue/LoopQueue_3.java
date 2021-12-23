package com.gitub.ybqdren.queue;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description 不使用size，但是需要浪费一个空间
 **/
public class LoopQueue_3<E> implements Queue<E>{
    private E[] data;
    private int front,tail;

    public LoopQueue_3(int capacity) {
        data = (E[])new Object[capacity + 1]; // 由于不使用size这个属性，因此我们还是需要浪费1个容量的空间

        this.front = 0;
        this.tail = 0;
    }

    public LoopQueue_3() {
        this(10);
    }

    @Override
    public boolean isEmpty() {
        // 注意，我们不再使用front和tail之间的关系来判断队列是否为空，而直接使用size
        return front == tail;
    }

    public int getCapacity(){
        return data.length-1 ;
    } // 浪费1个空间，所以实际有效空间应该为容量-1

    @Override
    public int getSize() {
        // 注意此时getSize的逻辑
        // 如果tail >= front ，非常简单，队列中的元素个数就是tail - front
        // 如果tail < front，说明我们的循环队列“循环”起来了，此时，队列中的元素的个数为： tail - front + data.length
        // 也可以理解成，此时，data中没有元素的数目为front - tail，
        // 整体元素个数就是data.length - (front - tail) = data.length + tail - front
        return tail >= front ? tail - front : tail - front + data.length ;
    }

    @Override
    public void enqueue(E e) {
        if((tail + 1) % data.length == front ){
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
    }

    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity + 1]; // 因为数组要浪费1个空间，所以数组容量要加1
        for(int i=0 ;i < getSize() ;i++){
            // 将data中的元素放到newData中去
            newData[i] = data[(i+front) % data.length]; // data中的值相对于newData中的值，是存在偏移的

            data = newData;
            front = 0;
            tail = getSize();
        }
    }



    @Override
    public E dequeue(){
        if(isEmpty()) { throw  new IllegalArgumentException("Cannot dequeue from an empty queue."); }

        E ret = data[front]; // 将出队的元素保存返回
        data[front] = null; // 将出队的位置设置为null
        front = (front+1) % data.length; // front向后移动一位

        // 缩容：等队列中的元素个数 == 队列容量的1/4时
        if(getSize() == getCapacity()/4 && getCapacity()/2 != 0){
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
        res.append(String.format("Queue: size = %d,capacity = %d \n",getSize(),getCapacity()));
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
        LoopQueue_3 queue = new LoopQueue_3();
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
