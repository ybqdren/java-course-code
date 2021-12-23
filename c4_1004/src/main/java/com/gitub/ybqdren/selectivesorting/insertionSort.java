package com.gitub.ybqdren.selectivesorting;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/9/4
 */
public class insertionSort {
    private insertionSort() {
    }

    // 通过新的循环不变量实现方法
    public static <E extends Comparable<E>> void sort_other(E[] arr){
        // arr[0,i] 未排序，arr[i...n]已排序
        for(int i = arr.length-1 ; i >= 0; i--){
            for(int j = i; j+1 < arr.length  ;j++){
                if(arr[j].compareTo(arr[j+1]) > 0){
                    swap(arr,j+1,j);
                }
            }
        }
    }

    public static <E extends Comparable<E>> void sort(E[] arr){
        for(int i=0 ; i<arr.length; i++){
            // 将arr[i] 插入到合适的位置
            for(int j = i;j-1 >= 0;j--){
                // 将当前的元素和它前一个元素进行比较，如果看到小的话 就进行交换
                if(arr[j].compareTo(arr[j-1]) < 0){
                    swap(arr,j,j-1);
                // 此时arr[i]以及来到了最小的位置，此时就可以停止插入操作了
                }else {
                    break;
                }
            }
        }
    }

    public static <E extends Comparable<E>> void sort2(E[] arr){
        for(int i =0;i<arr.length;i++){
            // 将arr[i]插入到合适的位置
            E t = arr[i]; // 使用t将arr[i]元素进行暂存
            int j; // 寻找arr[i]实际应该存放的位置
            // 在寻找正确插入位置的过程中 只做赋值操作
            for(j=i ; j-1 >=0 && t.compareTo(arr[j-1]) < 0 ; j--){
                arr[j] = arr[j-1];
            }
            // 找到真正的插入位置时 进行一次交换操作
            arr[j] = t;
        }
    }

    private static <E> void swap(E[] arr,int i,int j){
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int n = 10000;
        Integer[] arrRandom = ArrayGenerator.generateRandomAarry(n,n);
        long startTime = System.nanoTime();
//        Integer[] arrRandom = {1,2,8,0,4,6};
        sort_other(arrRandom);
        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;

        if(!SortingHelper.isSorted(arrRandom)){
            throw new RuntimeException("Sort Error");
        }

        System.out.println(time + " s");

/*        int[] dataSize = {10000,100000};
        for(int n: dataSize){
            System.out.println("Random Array:");

            Integer[] arrRandom = ArrayGenerator.generateRandomAarry(n,n);
            Integer[] arrRandom2 = Arrays.copyOf(arrRandom,arrRandom.length);
            SortingHelper.sortTest("InsertionSort",arrRandom);
            SortingHelper.sortTest("SelectiveSort",arrRandom);

            System.out.println("Ordered Array:");
            // 此时为一个有序的数组
            arrRandom = ArrayGenerator.generateOrderedAarray(n);
            arrRandom2 = Arrays.copyOf(arrRandom,arrRandom.length);
            SortingHelper.sortTest("InsertionSort",arrRandom);
            SortingHelper.sortTest("SelectiveSort",arrRandom);
        }*/
    }
}
