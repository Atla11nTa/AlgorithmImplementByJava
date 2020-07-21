package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目: 78. 子集
 * 思路:回溯法.
 */

public class Problem78 {
    private void backTrack(List<List<Integer>> res, List<Integer> cur, int[] nums, int start, int maxLen) {
        if (cur.size() == maxLen) {
            res.add(new ArrayList<>(cur));
        } else {
            for (int i = start; i < nums.length; i++) {
                cur.add(nums[i]);
                backTrack(res, cur, nums, i + 1, maxLen);
                cur.remove(cur.size() - 1);
            }
        }
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            backTrack(res, new ArrayList<>(), nums, 0, i);
        }
        return res;
    }
}
