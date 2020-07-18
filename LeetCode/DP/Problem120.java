package LeetCode.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目： 120. 三角形最小路径和
 *  地址： https://leetcode-cn.com/problems/triangle/
 *  思路： 典型的动态规划求最短路径。
 *  只是这里是三角形， [i,j]位置可由[i-1][j]和[i-1][j-1]两个位置走到
 *  所以状态转移方程为： dp[i][j] = min{dp[i-1][j-1],dp[i-1][j]}+triangle[i][j]。
 *  同样也可以压缩为一维矩阵，dp[i]，dp仅保存上一层的结果，每层更新dp数组时从右到左更新即可。
 */

public class Problem120 {
    public static int minimumTotal(List<List<Integer>> triangle) {
        int deep = triangle.size();
        int[] dp = new int[deep];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; j--) {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + triangle.get(i).get(j);
            }
            dp[0] = dp[0] + triangle.get(i).get(0);
        }
        int min = Integer.MAX_VALUE;
        for (int value : dp) {
            min = Math.min(min, value);
        }
        return min;
    }

    public static void main(String[] args) {
        Integer[][] map = new Integer[][]{
                {2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}
        };
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(map[0]));
        triangle.add(Arrays.asList(map[1]));
        triangle.add(Arrays.asList(map[2]));
        triangle.add(Arrays.asList(map[3]));
        System.out.println(minimumTotal(triangle));
    }
}
