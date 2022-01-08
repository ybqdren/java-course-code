package com.github.ybqdren.chapter1.dynamicprogramming;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/8 14:05
 * @package com.github.ybqdren.chapter1.dynamicprogramming
 * @description  509. 斐波那契数  https://leetcode-cn.com/problems/fibonacci-number/
 **/
public class p509 {
    public static void main(String[] args) {
        p509 instance = new p509();
        instance.fib_1(2);

    }


    // 方法二：带备忘录的递归解法



    // 方法一：

    /**
        递归树：
                         f(20)
              f(19)                f(18)
         f(18)    f(17)        f(17)    f(16)
        .......    .....          ..........
      f(1)  f(2)  f(1) f(2)

     从上面可以看出，想要计算原问题 f(20) ，就应该先计算出子问题 f(19) 和 f(18)
     要计算 f(19)，就要先计算出子问题 f(18) 和 f(17) , 以此类推
     最后当遇到 f(1) 或者 f(2) 的时候，结果已知，就能直接返回结果，递归树也不再向下生长了。

     观察上面这个递归树，可以很明显地发现算法低效的原因：存在大量重复计算，比如 f(18)
     被计算了两次，而且以 f(18) 为根的这个递归树体量巨大，多算一遍会耗费大量的时间
     更何况还不止 f(18) 这一个节点会被重复计算，所以这个算法非常的低效。


     -递归算法的复杂度计算？
        复杂度 = 子问题个数 * 解决一个子问题需要的时间

        >  子问题个数：
            即递归树中节点的总数。显然二叉树节点总数为指数级别的，所以求子问题个数的时间复杂度为 O(2^n)。

        > 解决一个子问题的时间：
            在本算法中，没有循环，只有 f(n-1) + f(n-2) 加法操作，所以时间复杂度为 O（1）

        所以此递归算法的复杂度为： O(2^n)，指数级别
     */
    public  int fib_1(int n) {
        if(n == 0){ return 0; }
        if(n == 1 || n == 2){ return 1; }
        return fib_1(n - 1) + fib_1(n - 2);
    }

}
