package DynamicProblem;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 题目: 换钱的最少货币数
 * 题目描述: 数组arr定义一系列货币数额, aim是目标金额, 请使用最少的货币数得到目标金额, 返回货币总数.
 * 题目分析: 抽象问题描述: 挑选数组元素使累加为aim, 每个元素可以多次挑选, 求最少的元素个数.
 * 解法1: 暴力回溯
 * 解法2: DP
 * dp[i]表示换取金额i所需最少货币数.
 * 对于每一个货币是否要选择, 就是dp[j] = min{dp[j- arr[i]]+1,dp[j}. 选择的话就是dp[j-arr[i]]+1,不选择就不变, 选择原则就是让dp[j]更小.
 *
 *
 */
public class MinCoins {
    public static int solution(int[] arr, int aim) {
        int[] dp = new int[aim + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        //考察货币arr[i]
        for (int i = 0; i < arr.length; i++) {
            // 考察数额从arr[i] - aim之间的所有数额
            for (int j = arr[i]; j <= aim; j++) {
                // 考虑是否要选择货币arr[i]
                dp[j] = Math.min(dp[j - arr[i]] + 1, dp[j]);
            }
        }
        return dp[aim] == Integer.MAX_VALUE - 1 ? -1 : dp[aim];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 3};
        System.out.println(solution(arr, 1));
    }
}
