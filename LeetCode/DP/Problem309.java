package LeetCode.DP;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * 思路: 把每天分为三个不同的状态,统计每种状态下的最大收益.
 * 状态分析, 以及状态转移需要理解.
 */

public class Problem309 {
    public static int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        // f[i][0]: 手上持有股票的最大收益
        // f[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益
        // f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益
        int[][] dp = new int[n][3];
        dp[0][0] = -prices[0];
        for (int i = 1; i < n; ++i) {
            // 继续昨天的持有或者昨天不持有的状态+今天买入
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            // 今天卖出就会进入不持有股票且是冷冻期的状态
            dp[i][1] = dp[i - 1][0] + prices[i];
            // 继续昨天的不持有,或者冷冻期过后的不持有, 选最大
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }
        return Math.max(dp[n - 1][1], dp[n - 1][2]);
    }

    public static void main(String[] args) {
        int[] prices = new int[]{1, 2, 4};
        System.out.print(maxProfit(prices));
    }
}
