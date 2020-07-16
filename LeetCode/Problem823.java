package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 题目：823.带因子的二叉树
 * 链接： https://leetcode-cn.com/problems/binary-trees-with-factors/
 * 思路： 动态规划。
 * dp[i]表示以A[i]作为根节点的二叉树个数。
 * 假设A[j] * A[k] == A[i]，那么dp[i] = dp[j] * dp[k]，加上其他情况就是： dp[i] += dp[j]*dp[k];因为是>1的正整数数组，而且对A[i]排序过
 * 所以计算dp[i]时，dp[j].dp[k]都已经有结果了。
 */
public class Problem823 {
    public static int numFactoredBinaryTrees(int[] A) {
        int len = A.length;
        //先排序, 保证在计算后面的结果时，已经得到前面的结果
        Arrays.sort(A);
        long[] dp = new long[len];
        Arrays.fill(dp, 1);
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < len; ++i) {
            hashMap.put(A[i], i);
        }
        int root,left,right;
        // 每个节点Ai作为根时
        for (int i = 0; i < len; ++i) {
            root = A[i];
            // 节点Aj作为其中一个子节点, 排序后保证
            for (int j = 0; j < i; ++j) {
                left = A[j];
                if (A[i] % left == 0) {
                    right = A[i] / A[j];
                    //检查另一个子节点是否存在于数组中
                    if (hashMap.containsKey(right)) {
                        dp[i] += dp[j] * dp[hashMap.get(right)];
                    }
                }
            }
        }
        //统计总数
        long res = 0;
        for (long x : dp) {
            res += x;
        }
        return (int)(res%1000000007);
    }

    public static void main(String[] args) {
        System.out.println(numFactoredBinaryTrees(new int[]{2, 4}));
    }
}
