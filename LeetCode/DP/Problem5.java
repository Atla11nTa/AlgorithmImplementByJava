package LeetCode.DP;

/**
 * 题目： 5. 最长回文子串
 * 思路： dp[j][i]表示j~i是否为回文子串，
 * 固定i，移动j，求出dp[j][i] = dp[j+1][i-1] && str[i] == str[j]。
 */

public class Problem5 {
    public static String longestPalindrome(String s) {
        if (s.length() == 0) {
            return s;
        }
        char[] strArr = s.toCharArray();
        int maxLen = Integer.MIN_VALUE;
        int beginIndex = -1;
        boolean[][] isPalindrome = new boolean[strArr.length][strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            isPalindrome[i][i] = true;
            for (int j = i - 1; j >= 0; j--) {
                //长度为2时
                if (j == i - 1 && strArr[i] == strArr[j]) {
                    isPalindrome[j][i] = true;
                }
                if (isPalindrome[j + 1][i - 1] && strArr[i] == strArr[j]) {
                    isPalindrome[j][i] = true;
                }
                if (isPalindrome[j][i] && i - j + 1 > maxLen) {
                    maxLen = i - j + 1;
                    beginIndex = j;
                }
            }
            if (1 > maxLen) {
                maxLen = 1;
                beginIndex = i;
            }
        }
        return s.substring(beginIndex, beginIndex + maxLen);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babda"));
    }
}
