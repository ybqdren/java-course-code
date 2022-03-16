package com.github.ybqdren.chapter1.dynamicprogramming.p322;

import java.util.Arrays;

/**
 * <h1>  凑零钱 </h1>
 *
 * @Author zhao wen
 * @Version 0.0.1
 * @Date 2022/3/14
 * https://leetcode-cn.com/problems/coin-change/
 **/
public class p322 {

    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    // 跳过无解的子问题
                    if(i - coins[j] < 0 ){continue;}
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
