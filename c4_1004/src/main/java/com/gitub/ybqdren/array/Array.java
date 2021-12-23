package com.gitub.ybqdren.array;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/9/8
 */
public class Array {
    private int[] data;
    private int size;

    public Array(int capacity) {
        this.data = new int[capacity];
        size = 0;
    }

    // 无参数的构造函数，默认数组的容量（capacity）为10
    public Array(){ this(10); }

    // 获取数组中的元素个数
    public int getSize(){ return size; }

    // 获取数组的容量
    public int getCapacity(){ return data.length; }

    public boolean isEmpty(){ return size == 0; }

    // 向所有元素后添加一个新元素
    public void addLast(int e){ add(size,e); }

    // 向所有元素前添加一个新元素
    public void addFirst(int e){ add(0,e);}

    // 在第index个位置插入一个新元素e
    public void add(int index,int e){
        if(size == data.length){ throw  new IllegalArgumentException("Addlast is fail,arr is full."); }

        if(index < 0 || index > size){ throw  new IllegalArgumentException("Addlast is fail++,arr is full."); }

        for(int i = size - 1 ; i>= index ; i--){
            data[i+1] = data[i];
        }

        data[index] = e;
        size++;
    }

    // 获取index索引位置的元素
    // 通过这样的封装，可以防止用户查询到没有使用到的空间
    public int get(int index){
        if(index < 0 || index >= size){ throw  new IllegalArgumentException("Get failed. Index is illegal."); }
        return data[index];
    }

    public void set(int index,int e){
        if(index < 0 || index >= size){ throw  new IllegalArgumentException("Get failed. Index is illegal."); }
        this.data[index] = e;
    }

    // 包含 搜索 和 查询
    public boolean isContain(int e){
        for(int i : data){
            if(i == e){ return true; }
        }
        return false;
    }

    public int find(int e){
        for(int i = 0 ; i < size ; i++){
            if(e == data[i]){
                return i;
            }
        }
        return -1;
    }

    public int remove(int index){
        if(index < 0 || index > size){ throw  new IllegalArgumentException("Remove is fail,arr is full."); }

        int ret = data[index];

        for(int i =index ; i<size-1 ; i++){
            data[i] = data[i+1];
        }

        size--;
        return ret;
    }

    // 从数组中删除第一个元素，返回删除的元素
    public int removeFirst(){
        return remove(0);
    }

    // 从数组中删除最后一个元素，返回删除的元素
    public int removeLast(){
        return remove(size-1);
    }

    // 只删除一个元素e
    public void removeElement(int e){
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
