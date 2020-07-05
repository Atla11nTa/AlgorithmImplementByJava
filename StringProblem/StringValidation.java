package StringProblem;

import java.util.Arrays;

/**
 * 题目：检测括号字符串的有效性
 * 补充题目： 返回最长有效长度
 * 题目分析： 对于普通问题，栈解决。
 * 补充问题使用DP解决。
 *
 */
public class StringValidation {
    public static boolean Solution(String str){
        char[] charStack = new char[str.length()];
        int stackLen = 0;
        char[] charArr = str.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            if(charArr[i] == '('){
                charStack[stackLen++] = '(';
            }else {
                if(stackLen==0)
                    return false;
                if(charStack[stackLen-1] == '(')
                    stackLen--;
                else
                    return false;
            }
        }
        return true;
    }

    /**
     * 补充问题
     * 1. 定义dp数组的含义，dp[i]表示以子串[0...i]的最长有效括号长度。
     * 2. 初始化，dp[i]为0
     * 3. 状态转移方程：
     * 如果str[i] == ')'，pre表示前面与str[i]对应的字符。如果这个字符是'('，那么就和str[i]构成有效的。
     * 那么dp[i] = dp[i-1]+2+dp[pre-1];
     */
    public static int getMaxLen(String str){
        char[] strArr = str.toCharArray();
        int[] dp = new int[str.length()];
        int pre = 0;
        for (int i = 1; i < str.length(); i++) {
            if(strArr[i] == ')'){
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && strArr[i - dp[i - 1] - 1] == '(') {
                    if (pre - 1>= 0) {
                        dp[i] = dp[i - 1] + 2 + dp[pre - 1];
                    } else{
                        dp[i] = dp[i - 1] + 2;
                    }
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i=0;i<dp.length;i++){
            max = Math.max(dp[i],max);
        }
        return max;
    }

    public static void main(String[] args) {
        String str = ")(())(";
        System.out.println(getMaxLen(str));
    }
}
