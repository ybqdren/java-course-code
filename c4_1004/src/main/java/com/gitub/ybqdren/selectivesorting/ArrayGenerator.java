package com.gitub.ybqdren.selectivesorting;

import java.util.Random;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/8/29
 *
 * 数组生成器
 */
public class ArrayGenerator {
    private ArrayGenerator() {
    }

    /**
     * 生成一个顺序的数组
     * @param n 数组的长度
     * @return
     */
    public static Integer[] generateOrderedAarray(int n){
        Integer[] arr = new Integer[n];
        for(int i=0 ; i<n ; i++){
            arr[i] = i;
        }
        return arr;
    }


    /**
     * 生成一个长度为n随机数组，每个数字的范围为[0,bound]
     * @param n 数组长度
     * @param bound 随机数生成最大的界
     * @return
     */
    public static Integer[] generateRandomAarry(int n,int bound){
        Integer[] arr = new Integer[n];
        Random rnd = new Random();
        for(int i = 0;i<n;i++){
            arr[i] = rnd.nextInt(bound);
        }
        return arr;
    }
}
