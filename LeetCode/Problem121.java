package LeetCode;

/**
 * 题目： 121. 买卖股票的最佳时机
 * 地址： https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 * 思路：min记录当前时间以前的最低价格。之后以最低价格买入，再在当天卖出的收益，找到最大收益即可
 */

public class Problem121 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int profit = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            profit = Math.max(profit, prices[i] - min);
        }
        return profit;
    }
}
