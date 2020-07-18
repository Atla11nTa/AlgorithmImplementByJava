package LeetCode.DP;

/**
 * 题目： 300.最长上升子序列
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 * 思路：
 * dp[i]记录以nums[i]结尾的最长上升子序列长度
 * 更新dp[i]时，遍历i之前的所有元素，若nums[i]大于nums[j]，那么dp[i] = dp[j]+1，因为要找最大值，所以找满足条件的最大的dp[j]就行
 */
public class Problem300 {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
