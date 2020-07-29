package LeetCode.DP;

import java.util.Arrays;

/**
 * 5462. 压缩字符串 II
 * 地址:https://leetcode-cn.com/problems/string-compression-ii/
 * 思路:没想出来,有空再看吧
 */

public class H_Problem5462 {

    public static int getLengthOfOptimalCompression(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n + 1][k + 2];
        for (int i = 0; i < n+1; i++) {
            Arrays.fill(dp[i], 0x3f);
        }
        char[] chars = s.toCharArray();
        dp[0][0] = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= k; j++) {
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i - 1][j]);
                int cnt = 0, del = 0;
                for(int l = i; l <= n; l++) {
                    cnt += chars[l - 1] == chars[i - 1] ? 1 : 0;
                    del += chars[l - 1] != chars[i - 1] ? 1 : 0;
                    if(j + del <= k) dp[l][j + del] = Math.min(dp[l][j + del], dp[i - 1][j] + 1 + (cnt >= 100 ? 3 : cnt >= 10 ? 2 : cnt >= 2 ? 1: 0));
                }
            }
        }
        return dp[n][k];
    }

    public static void main(String[] args) {
        System.out.print(getLengthOfOptimalCompression("aaabcccd", 2));
    }
}
