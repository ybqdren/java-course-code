package com.github.ybqdren.base;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/26 18:29
 * @package com.github.ybqdren.base
 * @description 局部变量对垃圾回收的影响
 **/
public class localVarGc {

    public void localvarGc1(){
        byte[] a = new byte[6 * 1024 * 1024];
        System.gc();
    }

    public void localvarGc2(){
        byte[] a = new byte[6 * 1024 * 1024];
        a = null;
        System.gc();
    }

    public void localvarGc3(){
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }

        System.gc();
    }

    public void localvarGc4(){
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }

        int c = 10;
        System.gc();
    }

    public void localvarGc5(){
        localvarGc1();
        System.gc();
    }

    public static void main(String[] args) {
        localVarGc localVarGc = new localVarGc();
//        localVarGc.localvarGc1();
//        localVarGc.localvarGc2();
//        localVarGc.localvarGc3();
//        localVarGc.localvarGc4();
        localVarGc.localvarGc5();
    }
}
