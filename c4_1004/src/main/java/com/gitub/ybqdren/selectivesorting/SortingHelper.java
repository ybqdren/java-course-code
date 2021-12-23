package com.gitub.ybqdren.selectivesorting;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/9/4
 */
public class SortingHelper {
    private SortingHelper(){}

    /**
     * 判断一个数组是否是有序的
     * @return
     */
    public static<E extends Comparable<E>> boolean isSorted(E[] arr){
        for(int i = 1;i < arr.length ; i++){
            // 比较前一个元素是否小于后一个元素
            if(arr[i-1].compareTo(arr[i]) > 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 帮助我们测试任意一个排序类型
     * @param sortName 排序算法的名称
     * @param arr 比较的数组
     */
    public static <E extends Comparable<E>> void sortTest(String sortName,E[] arr){
        long startTime = System.nanoTime();

        if(sortName.equals("SelectiveSort")){ SelectiveSort.sort(arr); }
         else if(sortName.equals("InsertionSort")){insertionSort.sort(arr);}
         else if(sortName.equals("InsertionSort2")){insertionSort.sort2(arr);}


        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        if(!SortingHelper.isSorted(arr)){ throw new RuntimeException("Sort Error"); }
        System.out.println(String.format("%s , n= %d : %f s",sortName,arr.length,time));
    }
}
