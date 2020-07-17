package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：60. 第k个排列
 * 相关题目：46.求全排列，47.含重复数的全排列
 * 地址： https://leetcode-cn.com/problems/permutation-sequence/
 * 给定n，代表数字集合1,2,3...n，拥有n!个排列，根据数字大小关系升序排序，返回第k个排序。
 *
 * 思路：
 * 暴力法：用回溯法求得所有排列，因为通过回溯法就是按大小顺序得到结果的，所以返回第k个结果即可。
 * 既然回溯法的结果有先后顺序，而且回溯法确定一次选择，后续的结果集大小也是可以确定的，因此可以比较k和后续结果集的大小进行剪枝。
 * 具体可以画出回溯树进行理解。
 *
 * ！！！回溯问题应该心里有一个回溯树。
 */
public class H_Problem60 {
    public String getPermutation(int n, int k) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        //记录当前的索引的数是否被使用过
        boolean[] isUsed = new boolean[n];
        return dfs(nums, new ArrayList<String>(), isUsed, 0, n, k);
    }

    private String dfs(int[] nums, List<String> levelList, boolean[] isUsed, int depth, int n, int k) {
        if (depth == n) {
            StringBuilder res = new StringBuilder();
            for (String s : levelList) res.append(s);
            return res.toString();
        }
        // 计算本层选择的后续排列数，以4个元素为例，选择了0层的元素，后续就会得到3!个排列。
        int cur = factorial(n - depth - 1);//当前的depth也就是索引，有多少排列数
        for (int i = 0; i < n; i++) {
            if (isUsed[i]) {
                continue;
            }
            // 如果cur<k，说明第k个排列不在该元素后，剪枝，选择同层下一个元素。
            if (cur < k) {
                k -= cur;
                continue;
            }
            levelList.add(nums[i] + "");
            isUsed[i] = true;
            return dfs(nums, levelList, isUsed, depth + 1, n, k);
        }
        return null;
    }

    //返回n的阶乘
    private int factorial(int n) {
        int res = 1;
        while (n > 0) {
            res *= n--;
        }
        return res;
    }
}
