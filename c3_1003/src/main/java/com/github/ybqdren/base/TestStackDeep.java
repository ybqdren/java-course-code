package com.github.ybqdren.base;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/26 16:55
 * @package com.github.ybqdren.base
 * @description
 **/
public class TestStackDeep {
    private static int count = 0;
    public static void recursion(){
        count ++;
        recursion();
    }

    public static void main(String[] args) {
        try {
            recursion();
        }catch (Throwable e){
            System.out.println("deep of calling = "+ count);
            e.printStackTrace();
        }
    }
}

