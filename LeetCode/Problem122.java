package LeetCode;

/**
 * 题目： 买卖股票的最佳时机II
 * 地址： https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 * 思路： 只要比前一日高，就在前一日买，在今日卖，反正可以多次买卖，这样最后肯定可以得到最大收益。
 */
public class Problem122 {
    public int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }
}
