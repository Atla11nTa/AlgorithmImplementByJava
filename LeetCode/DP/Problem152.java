package LeetCode.DP;

/**
 * 152. 乘积最大子数组
 * 思路: dp[i][0]表示以i结尾最大的数
 * dp[i][1]表示以i结尾最小的数.
 */
public class Problem152 {
    public static int maxProduct(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        int res = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] * nums[i], Math.max(nums[i], dp[i - 1][1] * nums[i]));
            res = Math.max(res, dp[i][0]);
            dp[i][1]= Math.min(dp[i - 1][0] * nums[i], Math.min(nums[i], dp[i - 1][1] * nums[i]));
            res = Math.max(res, dp[i][1]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, -3, 7};
        System.out.print(maxProduct(nums));
    }
}
