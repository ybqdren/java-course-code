package com.gitub.ybqdren.array;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/9/8
 */
public class Main {
    public static void main(String[] args) {
/*        Array arr = new Array(20);
        for(int i=0 ; i<10 ; i++){
            arr.addLast(i);
        }
        System.out.println(arr);
        arr.add(1,100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.remove(1);
        System.out.println(arr);*/

/*        Array_g<Integer> arr=new Array_g<>(20);
        for (int i=0;i<10;i++) {
            arr.addLast(i);
        }
        System.out.println(arr);

        arr.add(1,100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.remove(2);
        System.out.println(arr);

        arr.removeElement(4);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);*/

        Array_d<Integer> arr=new Array_d<>();
        for (int i=0;i<10;i++) {
            arr.addLast(i);
        }
        System.out.println(arr);

        arr.add(1,100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.remove(2);
        System.out.println(arr);

        arr.removeElement(4);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);

    }
}
