package com.github.ybqdren.base;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/26 16:55
 * @package com.github.ybqdren.base
 * @description  示例 2-4
 **/
public class TestStackDeep2 {
    private static int count = 0;
    public static void recursion(long a,long b,long c){
        count ++;
        recursion(a,b,c);
    }

    public static void recursion(){
        count ++;
        recursion();
    }

    public void localvar1(){
        int a = 0;
        System.out.println(a);
        int b = 0;
    }

    public void localvar2(){
        {
            int a = 0;
            System.out.println(a);
        }
        int b = 0;
    }




    public static void main(String[] args) {
        try {
//            recursion();
            recursion(0L,0L,0L);
        }catch (Throwable e){
            System.out.println("deep of calling = "+ count);
            e.printStackTrace();
        }
    }
}

