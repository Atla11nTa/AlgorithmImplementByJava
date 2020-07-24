package LeetCode.DP;

/**
 * 题目： 55.跳跃游戏
 * 思路： 典型DP
 * dp[i]表示能到达i. 双重循环更新dp[i]即可。
 */
public class Problem55 {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        boolean[] dp = new boolean[len];
        dp[0] = true;
        for (int i = 1; i < len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && nums[j] >= i - j) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len - 1];
    }
}
