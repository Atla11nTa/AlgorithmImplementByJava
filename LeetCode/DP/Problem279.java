package LeetCode.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 279. 完全平方数
 * 思路: 零钱兑换问题, 从大到小去试.
 */
public class Problem279 {
    public static int numSquares(int n) {
        List<Integer> numList = new ArrayList<>();
        int num = 1;
        while (num*num <= n) {
            numList.add(num * num);
            num++;
        }
        int numLen = numList.size();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 100000);
        for (int i = numLen - 1; i >= 0; i--) {
            int curNum = numList.get(i);
            dp[curNum] = 1;
            for (int j = curNum; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - curNum] + 1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.print(numSquares(12));
    }
}
