package LeetCode.DP;

/**
 * 题目: 44. 通配符匹配
 * 地址: https://leetcode-cn.com/problems/wildcard-matching/
 * 思路: 这道题与另一道有区别,这个不能使用递归或者回溯,很容易超时, 需要采用动态规划.
 * dp[i][j]表示字符串从0到i与通配符从0到j的匹配情况.
 * 仔细理解动态规划.
 */
public class H_Problem44 {
    private static boolean match(char[] str, int si, char[] exp, int ei) {
        // 匹配完成
        if (ei == exp.length) {
            return si == str.length;
        }
        // 如果当前通配符不是 *
        if (exp[ei] != '*') {
            return si != str.length && (exp[ei] == '?' || exp[ei] == str[si]) && match(str, si + 1, exp, ei + 1);
        }

        while (si != str.length) {
            if (match(str, si, exp, ei + 1)) {
                return true;
            }
            si++;
        }
        return match(str, si, exp, ei + 1);
    }
    public static boolean isMatch(String s, String p) {
        char[] str = s.toCharArray();
        char[] exp = p.toCharArray();
        return match(str, 0, exp, 0);
    }

    public static boolean isMatch2(String s, String p) {
        int m = s.length();
        int n = p.length();
        char[] str = s.toCharArray();
        char[] exp = p.toCharArray();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        //
        for (int i = 1; i <= n; ++i) {
            if (exp[i - 1] == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                // 若j-1是 *
                if (exp[j - 1] == '*') {
                    /**
                     * 假设
                     * str: a c d c b
                     * exp: a * c ? b
                     * i = 2, j = 2时,
                     * 如果这个星号不使用, 那么dp[2][2] = dp[2][1], 等于之前的答案
                     * 如果这个星号使用, 那么dp[2][2] = dp[1][2], 等于上一个答案
                     * 两个有一个成功, dp[2][2] = true.
                     */
                    // 选择不匹配的话, 当前答案就和dp[i][j-1]一致
                    // 选择匹配的, 当前答案就和dp[i-1][j]一致.
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
                // 匹配一位.
                else if (exp[j - 1] == '?' || str[i - 1] == exp[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String str = "aa";
        String exp = "*";
        System.out.print(isMatch(str, exp));
    }
}
