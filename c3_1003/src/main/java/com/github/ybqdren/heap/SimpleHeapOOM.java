package com.github.ybqdren.heap;

import java.util.ArrayList;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/24 9:44
 * @package com.github.ybqdren.heap
 * @description
 * 堆溢出的典型，一个 ArrayList 对象总是持有 byte 数组的强引用，导致 byte 数据无法回收 （然而在 jvm 17 中没有抛出异常）
 **/
public class SimpleHeapOOM {
    public static void main(String[] args) {
        ArrayList<byte[]> list = new ArrayList<byte[]>();
        for(int i=0 ; i<1024 ; i++){
            list.add(new byte[1024 * 1024*1024*1024]);
        }
    }
}
