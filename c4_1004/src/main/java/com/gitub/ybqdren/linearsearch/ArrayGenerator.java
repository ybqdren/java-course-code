package com.gitub.ybqdren.linearsearch;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/8/29
 *
 * 数组生成器
 */
public class ArrayGenerator {
    private ArrayGenerator() {
    }

    public static Integer[] generateOrderedAarray(int n){
        Integer[] arr = new Integer[n];
        for(int i=0 ; i<n ; i++){
            arr[i] = i;
        }
        return arr;
    }
}
