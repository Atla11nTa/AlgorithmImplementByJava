package LeetCode;

/**
 * 题目: 198.打家劫舍
 * 思路:简单dp. dp[i]只和dp[i-1]与dp[i-2]有关.
 * 转移方程: dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
 */
public class Problem198 {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        int len = nums.length;
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[len - 1];
    }
}
