package LeetCode.BinarySearch;

/**
 * 题目: 410. 分割数组的最大值
 * 类型: 最大值最小值问题. 画家问题
 * 思路: 二分法找每个子数组和的最大值最小.
 * 不断的二分尝试子数组的最大和, 看需要的切分数目, 若切分数大于要求的m, 说明每个子数组的和需要再大点(更少的画家干更多的活).
 */

public class H_Problem410 {
    private static long check(int[] nums, long max) {
        int splitCount = 1;
        long sum = 0;
        for (var num : nums) {
            // 说明无法分割为最大max的块
            if (num > max) {
                return Integer.MAX_VALUE;
            }
            if (sum + num > max) {
                sum = num;
                splitCount++;
            } else {
                sum += num;
            }
        }
        return splitCount;
    }
    public static int splitArray(int[] nums, int m) {
        long sum = 0;
        long max = Integer.MIN_VALUE;
        for (var num : nums) {
            max = Math.max(num, max);
            sum += num;
        }
        long left = max;
        long right = sum;
        long mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            // 如果分割数大于m
            if (check(nums, mid) > m) {
                left = mid + 1;
            } else if (check(nums, mid) <= m) {
                right = mid - 1;
            }
        }
        return (int)left;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2147483647};
        System.out.print(splitArray(nums, 2));
    }
}
