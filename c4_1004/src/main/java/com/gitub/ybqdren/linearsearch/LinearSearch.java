package com.gitub.ybqdren.linearsearch;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/8/29
 *
 * 线性查找法实现 在data数组中查找16
 *
 * input: 数组，和目标元素
 * output: 目标元素所在的索引；若不存在，返回-1
 */
public class LinearSearch{

    public static void main(String[] args) {
        int[] dataSize = {1000000,10000000};
        for(int n:dataSize){
            Integer[] data = ArrayGenerator.generateOrderedAarray(n);

            long startTime = System.nanoTime();
            for(int k = 0;k<100;k++){
                LinearSearch.lsearch(data,n);
            }
            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("n="+n+" 100 runs : "+time+"s ");
        }
    }

    /**
     * 线性查找算法 返回下标
     * @param target
     * @return 将其改造为泛型方法  ，用户可以过根据自己的代码进行查找
     *
     * Java泛型：
     * 不可以使用基本数据类型，只能是类对象
     * <基本数据类型    ： boolean byte char short int long float double>
     * <基本类型的包装类： Boolean Byte Character Short Integer Long Float Double>
     */
    public static <E> int lsearch(E[] data,E target){
        // 查找到指定的数字
        for(int i=0;i< data.length;i++){
            // 判断两个类对象相等时 应该使用equal
            // 判断值相等使用 ==  (类对象使用只会比较引用地址)
            if(data[i].equals(target)){ return i; }
        }

        return -1;
    }
}
