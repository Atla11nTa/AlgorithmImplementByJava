package LeetCode.BinarySearch;

/**
 * 题目: 287. 寻找重复数
 * 思路: 二分法
 * 这道题的二分很巧妙, 目的是为了从1-n找重复的那个数, 为了加速寻找, 采用二分, 每次遍历统计小于等于mid的数, 如果count大于mid, 说明目标数在mid即左边, 否则在右边.
 * 这个二分不是从数组出发,而是从找目标数出发, 目标数的集合是一个1-n的有序序列,所以可以二分.
 */

public class Problem287 {
    public int findDuplicate(int[] nums) {
        int left = 1;
        int right = nums.length - 1;
        int mid;
        int count;
        while (left < right) {
            mid = left + (right - left) / 2;
            count = 0;
            for (var num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            if (count > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
