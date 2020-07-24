package LeetCode.DP;

import java.util.Arrays;

/**
 * 题目：322. 零钱兑换
 * 思路：
 * 不需要排序，第一个循环遍历coins数组，第二个循环遍历coins[i]到amount之间， 考虑是否选择coins[i]
 */

public class Problem322 {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        //考察货币arr[i]
        for (int i = 0; i < coins.length; i++) {
            // 考察数额从arr[i] - aim之间的所有数额
            for (int j = coins[i]; j <= amount; j++) {
                // 考虑是否要选择货币arr[i]
                dp[j] = Math.min(dp[j - coins[i]] + 1, dp[j]);
            }
        }
        return dp[amount] == Integer.MAX_VALUE - 1 ? -1 : dp[amount];
    }
}
