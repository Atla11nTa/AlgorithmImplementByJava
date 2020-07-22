package LeetCode.TwoPointer;

import java.util.Arrays;

/**
 * 题目: 16.最接近的三数之和.
 * 思路: 双指针求三数之和的思路完全一致. 只不过这里是找最接近的
 */
public class Problem16 {
    private int twoSumClosest(int[] nums, int begin, int end, int target) {
        int cur;
        // 保存与答案的差值
        int res = Integer.MAX_VALUE;
        while (begin < end) {
            cur = nums[begin] + nums[end];
            // 最佳答案
            if (cur == target) {
                return cur;
            }
            // 当前和大于target
            else if (cur > target) {
                // 因为测试集中没有重复元素,减一次即可
                end--;
                res = Math.abs(cur - target) < Math.abs(res) ? cur - target : res;
            }
            // 如果当前和小于target
            else {
                res = Math.abs(cur - target) < Math.abs(res) ? cur - target : res;
                begin++;
            }
        }
        return res + target;
    }
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = Integer.MAX_VALUE;
        int cur = 0;
        for (int i = 0; i <= nums.length - 3; i++) {
            cur = nums[i];
            cur += twoSumClosest(nums, i + 1, nums.length - 1, target - cur);
            // 保留最接近的值
            res = Math.abs(cur - target) < Math.abs(res) ? cur - target : res;
        }
        return res + target;
    }
}
