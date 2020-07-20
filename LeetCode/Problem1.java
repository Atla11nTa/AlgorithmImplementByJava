package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目： 1. 两数之和
 * 地址：https://leetcode-cn.com/problems/two-sum/
 * 思路：
 * 因为是返回索引，所以不能排序，双指针的思路不可取。
 * 采用哈希表存储每个元素，元素值为key，索引为value.
 * 遍历数组找target-nums[i]是否存在于表中，并且检查其索引是否为i，若不是i说明找到了答案。
 */

public class Problem1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        // 把元素先保存在哈希表中
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            // 目标元素存在于哈希表中，且不是i。
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        return new int[0];
    }
}
