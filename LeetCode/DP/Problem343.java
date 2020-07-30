package LeetCode.DP;


/**
 * 题目: 343. 整数拆分
 * 思路: 动态规划, 二次遍历的动态规划.
 * 注意一点就是求dp[i]的最大值时, 有可能j比dp[j]更大.
 */
public class Problem343 {
    public static int integerBreak(int n) {
        if (n == 1) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 2; j < i; j++) {
                // 有可能j比dp[j]更大, 若更大就是不拆分的意思.
                dp[i] = Math.max(dp[i], Math.max(dp[j], j) * (i - j));
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.print(integerBreak(10));
    }
}
