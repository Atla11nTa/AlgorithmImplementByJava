package LeetCode.DP;

import java.util.Arrays;

/**
 * 题目： 354. 俄罗斯套娃信封问题
 * 地址：https://leetcode-cn.com/problems/russian-doll-envelopes/
 * 相关： 双排序问题，找最大收益。
 * 思路： 先按左边界排序，然后找另一个边界的最长递增子序列长度。
 * 排序时，如果左边界相等，就按右边界降序排列。为什么？
 * 因为左边界相等的时候，即使右边界较大，也是不符合要求的，无法放入信封，所以按降序排列，破坏递增顺序。
 */

public class H_Problem354 {
    public static int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        //先按左边界排序
        Arrays.sort(envelopes,(a,b)->{
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });
        //再找右边界的最长递增子序列长度。
        int[] nums = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            nums[i] = envelopes[i][1];
        }

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxLen = 1;
        for (int i = 1; i < nums.length; i++) {
            int maxPre = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxPre = Math.max(maxPre, dp[j]);
                }
            }
            dp[i] = maxPre + 1;
            maxLen = Math.max(dp[i], maxLen);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[][] envelopes = new int[][]{
                {1, 3}, {3, 5}, {6, 7}, {6, 8}, {8, 4}, {9, 5}
        };
        System.out.println(maxEnvelopes(envelopes));

    }
}
