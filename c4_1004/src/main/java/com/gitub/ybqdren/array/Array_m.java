package com.gitub.ybqdren.array;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/9/8
 */
public class Array_m<E>{
    private E[] data;
    private int size;

    public Array_m(int capacity) {
        this.data = (E[])new Object[capacity];
        size = 0;
    }

    // 无参数的构造函数，默认数组的容量（capacity）为10
    public Array_m(){ this(10); }

    // 获取数组中的元素个数
    public int getSize(){ return size; }

    // 获取数组的容量
    public int getCapacity(){ return data.length; }

    public boolean isEmpty(){ return size == 0; }

    // 向所有元素后添加一个新元素
    public void addLast(E e){ add(size,e); }

    // 向所有元素前添加一个新元素
    public void addFirst(E e){ add(0,e);}

    // 在第index个位置插入一个新元素e
    public void add(int index,E e){

        if(index < 0 || index > size){ throw  new IllegalArgumentException("Addlast is fail,arr is full."); }

        // 对数组进行扩容 用2*当前数组容量相当于说 要扩容多少和当前数组中有多少的元素是相关的
        if(size == data.length) {
            resize(2 * data.length);
        }

        try {
            for(int i = size-1 ; i>= index ; i--){
                data[i+1] = data[i];
            }
            data[index] = e;
        }catch (ArrayStoreException ee){
            ee.printStackTrace();
        }

        size++;
    }

    // 给数组扩容，用旧数组的引用指向新数组，完成数组的扩容
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for(int i = 0 ; i<size ; i++){
            newData[i] = data[i];
        }

        data = newData;
    }

    // 获取index索引位置的元素
    // 通过这样的封装，可以防止用户查询到没有使用到的空间
    public E get(int index){
        if(index < 0 || index >= size){ throw  new IllegalArgumentException("Get failed. Index is illegal."); }
        return data[index];
    }

    // 取出数组中最后一个元素 & 第一个元素
    public E getLast(){
        return get(size - 1);
    }

    public E getFirst(){
        return get(0);
    }


    public void set(int index,E e){
        if(index < 0 || index >= size){ throw  new IllegalArgumentException("Get failed. Index is illegal."); }
        this.data[index] = e;
    }

    // 包含 搜索 和 查询
    public boolean isContain(E e){
        for(E i : data){
            if(i.equals(e)){ return true; }
        }
        return false;
    }

    public int find(E e){
        for(int i = 0 ; i < size ; i++){
            if(data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    public E remove(int index){
        if(index < 0 || index > size){ throw  new IllegalArgumentException("Remove is fail,arr is full."); }

        E ret = data[index];

        for(int i =index ; i<size-1 ; i++){
            data[i] = data[i+1];
        }

        size--;
        data[size] = null; // loitering objects 垃圾回收技术  != memory leak 提醒jvm进行回收

        // 缩减数组的大小
        // 采用lazy策略 防止复杂度 震荡
        if(size == data.length/4 && data.length / 2 != 0){
            resize(data.length / 2);
        }

        return ret;
    }

    // 从数组中删除第一个元素，返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    // 从数组中删除最后一个元素，返回删除的元素
    public E removeLast(){
        return remove(size-1);
    }

    // 只删除一个元素e
    public void removeElement(E e){
        int index = find(e);
        if(index != -1){
            remove(index);
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size= %d , capacity= %d\n",size,data.length));
        res.append("[");
        for(int i = 0 ; i < size ; i++){
            res.append(data[i]);
            if(i != size - 1){
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }

}
