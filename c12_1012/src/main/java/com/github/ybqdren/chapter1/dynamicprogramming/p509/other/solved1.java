package com.github.ybqdren.chapter1.dynamicprogramming.p509.other;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/8 15:13
 * @package com.github.ybqdren.chapter1.dynamicprogramming.other
 * @description
 **/
public class solved1 {
    public static void main(String[] args) {

    }


    // 方法三：通项公式
    public static int fib_3(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibN = Math.pow((1 + sqrt5) / 2,n) - Math.pow((1 - sqrt5) / 2,n);
        return (int) Math.round(fibN / sqrt5);
    }

    // 方法二：矩阵快速幂
    public static int fib_2(int n) {
        if(n < 2){return n;}

        int[][] q = {{1,1},{1,0}};
        int[][] res = pow(q,n - 1);
        return res[0][0];
    }

    public static int[][] pow(int[][] arr,int n){
        int[][] ret = {{1,0},{0,1}};
        while (n > 0){
            if((n & 1) == 1){
                ret = multiply(ret,arr);
            }
            n >>= 1;
            arr = multiply(arr,arr);
        }
        return ret;
    }

    public static int[][] multiply(int[][] arr,int[][] brr){
        int[][] c = new int[2][2];
        for(int i=0;i < 2;i++){
            for(int j=0;j < 2;j++){
                c[i][j] = arr[i][0] * brr[0][j] + arr[i][1] * brr[1][j];
            }
        }

        return c;
    }

    // ----

    // 方法一：动态规划
    public static int fib_1(int n) {
        if(n < 2){return n;}
        int p = 0,q = 0,r = 1;
        for(int i = 2 ; i<= n ; ++i){
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
    // -------
}
