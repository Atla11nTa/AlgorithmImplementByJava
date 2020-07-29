package LeetCode.DP;

import java.util.Arrays;

/**
 * 题目:312戳气球
 * 思路: 思路看下方, 建议先看分治法
 */
public class H_Problem312 {
    /**
     * 分治解法
     * 关键在于如何划分子问题.
     * 思路: 如何划分区间呢?
     * 对于气球k, 先求k左边的解和k右边的解,再与戳破k比较,选择一个最大值作为本轮的解
     */
    public int[][] rec;
    public int[] val;

    public int maxCoins1(int[] nums) {
        int n = nums.length;
        val = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            val[i] = nums[i - 1];
        }
        val[0] = val[n + 1] = 1;
        rec = new int[n + 2][n + 2];
        for (int i = 0; i <= n + 1; i++) {
            Arrays.fill(rec[i], -1);
        }
        return solve(0, n + 1);
    }

    // 戳破k点气球, 再寻找[left,right]区间内的解(不包含边界),尝试每一个k点,最大值就是答案
    public int solve(int left, int right) {
        if (left >= right - 1) {
            return 0;
        }
        // 记忆,避免重复计算,因为这整个过程重复计算特别多.
        if (rec[left][right] != -1) {
            return rec[left][right];
        }
        int max = Integer.MIN_VALUE;
        // 找k点, 每一个点都试一下, 最大值就是本区间的答案
        for (int k = left + 1; k < right; k++) {
            // 戳破k点气球
            int sum = val[left] * val[k] * val[right];
            sum += solve(left, k) + solve(k, right);
            rec[left][right] = Math.max(rec[left][right], sum);
        }
        return rec[left][right];
    }

    /**
     * dp解法
     * dp解法思路与上面类似,并且时间复杂度也相同.
     */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 2][n + 2];
        int[] val = new int[n + 2];
        val[0] = val[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            val[i] = nums[i - 1];
        }
        // 从右到左计算每个区间的最大值保存起来.
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j <= n + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    int sum = val[i] * val[k] * val[j];
                    sum += dp[i][k] + dp[k][j];
                    dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }
        return dp[0][n + 1];
    }


}
