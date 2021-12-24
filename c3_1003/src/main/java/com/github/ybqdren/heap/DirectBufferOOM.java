package com.github.ybqdren.heap;

import java.nio.ByteBuffer;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/24 10:20
 * @package com.github.ybqdren.heap
 * @description 不断地申请直接内存，并最终可能导致内存溢出
 **/
public class DirectBufferOOM {
    public static void main(String[] args) {
        for(int i=0 ; i < 1024 ;i++){
            ByteBuffer.allocateDirect(1024 * 1024 * 1024);
            System.out.println(i);
//            System.gc();
        }
    }
}
