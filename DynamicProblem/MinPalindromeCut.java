package DynamicProblem;

/**
 * 题目： 回文最少分割数
 * 题目描述： 给定一个字符串str，将str分割为多个回文字符串的最小分割数。
 * 算法思路：动态规划解法。套路是动态规划求回文子串，在计算过程中增加一个求切割数。
 * dp[i]表示，从0开始到i这段子串需要的最小切割数，从左到右遍历，计算dp[i]，dp[len-1]就是结果
 * 状态转移方程： dp[i] = min{dp[j-1]+1}（其中str[j][i]是回文串） j从i遍历至0，若str[j...i]是回文串，那么切割数就是dp[j-1]+1，所以找到一个最小的即可。注意j等于0时，dp[j-1]越界
 */
public class MinPalindromeCut {
    public static int Solution(String str){
        char[] strArr = str.toCharArray();
        int[] dp = new  int[strArr.length];
        boolean[][] isPalindrome = new boolean[dp.length][dp.length];
        for(int i=0;i<strArr.length;i++){
            dp[i] = Integer.MAX_VALUE;
            //从i向前遍历，dp[i]之前的已经计算了，所以就考察str[j...i]是不是回文串
            for(int j=i;j>=0;j--){
                //求回文子串的过程
                if (strArr[i] == strArr[j] && (i - j < 2 || isPalindrome[j+1][i-1])) {
                    isPalindrome[j][i] = true;
                }
                if(isPalindrome[j][i]){
                    if (j == 0) {
                        dp[i] = Math.min(dp[i], 0);
                    } else {
                        //j=0时越界
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    }
                }
            }
        }
        return dp[strArr.length-1];
    }

    public static void main(String[] args) {
        String str = "ABCDC";
        System.out.println(Solution(str));
    }
}
