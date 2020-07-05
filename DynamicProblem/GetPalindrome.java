package DynamicProblem;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 题目： 添加最小的字符，使得字符串整体都是回文字符串。
 * 题目描述：“ABAB” -> "ABABA"
 * 思路分析：假设[i...j]表示字符串从i到j的子串。
 * 那么添加最少字符使得[i...j]变为回文子串的方法有两种。
 * 1.先将[i...j-1]变为回文子串，然后两边加上str[j]
 * 2. 先将[i+1...j]变为回文子串，然后两边加上str[i]
 * 这两种方法的选择取决于[i...j-1]变为回文子串所需字符少，还是[i+1...j]变为回文子串所需字符少。这就构成了最优子结构，考虑DP法。
 * 定义dp[i][j]表示子串[i...j]变为回文子串所需最小的字符数。
 * 然后根据求解的dp数组得到最终答案。
 */

public class GetPalindrome {
    /**
     * 求解dp数组
     * 1. 当[i...j]长度为1时，dp[i][j] = 0;
     * 2. 当[i...j]长度为2时，若str[i] == str[j]，dp[i][j] = 0;若不等，dp[i][j]为1
     * 3. 当[i...j]长度超过2时，若str[i] == str[j]，dp[i][j] = dp[i+1][j-1];若不等，dp[i][j] = min{dp[i][j-1]+1, dp[i+1][j]+1}
     */
    public static int[][] getDp(String str){
        char[] strArr = str.toCharArray();
        int len = str.length();
        //dp[i][j]表示将子串i..j变为回文串需要的最小字符串。
        int[][] dp = new int[len][len];
        for(int j=1;j<len;j++){
            //长度为2时d求解dp[i][j]
            dp[j - 1][j] = strArr[j - 1] == strArr[j] ? 0 : 1;
            //子串长度大于2时求解
            for (int i = j - 2; i >= 0; i--) {
                if (strArr[i] == strArr[j]) {
                    dp[i][j] = dp[i+1][j-1];
                }else {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
                }
            }
        }
        return dp;
    }
    public static String Solution(String str){
        char[] originStrArr = str.toCharArray();
        int originStrLen = str.length();
        int[][] dp = getDp(str);
        char[] resStrArr = new char[originStrLen + dp[0][originStrLen - 1]];

        int i = 0;
        int j = originStrLen - 1;
        int resLeft = 0;
        int resRight = resStrArr.length-1;
        //根据dp数组构建新数组，从两头往中间构造。
        while (i <= j) {
            if(originStrArr[i] == originStrArr[j]){
                resStrArr[resLeft++] = originStrArr[i];
                resStrArr[resRight--] = originStrArr[j];
                i++;
                j--;
            }else {
                //说明应该选择在两头加str[j]，
                if (dp[i][j - 1] < dp[i + 1][j]) {
                    resStrArr[resLeft++] = originStrArr[j];
                    resStrArr[resRight--] = originStrArr[j];
                    j--;
                }else {
                    resStrArr[resLeft++] = originStrArr[i];
                    resStrArr[resRight--] = originStrArr[i];
                    i++;
                }
            }
        }
        return Arrays.toString(resStrArr);
    }

    public static void main(String[] args) {
        String str = "AABC";
        String res = Solution(str);
        System.out.println(res);
    }
}
