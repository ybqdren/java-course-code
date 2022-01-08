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


    // 方法二：矩阵快速幂


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

}
