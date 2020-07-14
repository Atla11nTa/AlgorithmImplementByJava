package DynamicProblem;

/**
 * 题目: 把数字翻译成字符串
 * 题目描述: 1->'a' , 2->'b'... 25->'z'.给定一个数字,求有多少种不同的翻译方法
 * 因为字符最多两位，也就是每两位或者一位数字组成一个字符。
 * 从i到末尾这部分数字，可以分为把i位置数字单独翻译和i/i+1两位共同翻译两种情况。
 * dp[i]表示从i到末尾部分数字的翻译方法，那么dp[i] = dp[i+1] + dp[i+2]*isChar(i,i+1)。
 */

public class CountOfIntToString {
    public static int solution(int num) {
        char[] numArr = String.valueOf(num).toCharArray();
        int n = numArr.length;
        int[] dp = new int[n];
        dp[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if ((numArr[i] - '0') * 10 + (numArr[i + 1] - '0') <= 25) {
                if (i < n - 2) {
                    dp[i] = dp[i + 1] + dp[i + 2];
                } else {
                    dp[i] = dp[i + 1] + 1;
                }
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(solution(12258));
    }
}
