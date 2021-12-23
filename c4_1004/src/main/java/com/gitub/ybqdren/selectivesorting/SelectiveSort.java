package com.gitub.ybqdren.selectivesorting;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/9/3
 * 选择排序
 */
public class SelectiveSort<E> {

    private SelectiveSort(){

    }


    // 换一种方式实现选择排序
    public static <E extends Comparable<E>> void sort_other(E[] arr){
        // arr[i...arr.length-1]  循环不变量  arr[i...arr.length-1]是有序的 arr[0...i]是无序的
        for(int i = arr.length-1; i>=0 ; i--){
            int minIndex = i;
            for(int j=i-1 ; j >= 0 ; j--){
                if(arr[j].compareTo(arr[minIndex]) < 0){
                    minIndex = j;
                }
            }

            swap(arr,i,minIndex);
        }
    }

    public static <E extends Comparable<E>> void sort(E[] arr){
        // 维持一个循环不变量： arr[0...i] 是有序的 ； arr[i...n]是无序的
        for(int i =0 ; i<arr.length ; i++){
            int minIndex = i;
            // 选择arr[i...n]中的最小值的索引
            for(int j=i ;j < arr.length ; j++){
                if(arr[j].compareTo(arr[minIndex]) < 0){
                    minIndex = j;
                }

                swap(arr,i,minIndex);
            }
        }
    }

    private static <E> void swap(E[] arr,int i,int j){
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
/*        int n = 10000;
        Integer[] arrRandom = ArrayGenerator.generateRandomAarry(n,n);
        long startTime = System.nanoTime();
        sort(arrRandom);
        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;

        if(!SortingHelper.isSorted(arrRandom)){
            throw new RuntimeException("SelectiveSort Error");
        }

        System.out.println(time + " s");*/

/*        int n = 10000;
        Integer[] arrRandom = ArrayGenerator.generateRandomAarry(n,n);
        SortingHelper.sortTest("SelectiveSort",arrRandom);*/

/*
        Integer[] arr = {1,3,5,62,2};
        sort_other(arr);*/

        int[] dataSize = {10000,100000};
        for(int n: dataSize){
            Integer[] arrRandom = ArrayGenerator.generateRandomAarry(n,n);
            SortingHelper.sortTest("SelectiveSort",arrRandom);
        }
    }
}
