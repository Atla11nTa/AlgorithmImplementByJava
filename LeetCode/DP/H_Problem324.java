package LeetCode.DP;

import java.util.Arrays;

/**
 * 324. 摆动排序 II
 * 完美洗牌问题.
 * 思路:
 * 1. 先排序, 再顺序的插入即可, 注意一点就是按降序插入, 因为不降序插入的话,中间两个数有可能插入到相邻位置,不满足条件.
 * 2. 思路2, 快速选择算法找中位数. 然后根据中位数分为左中右三部分, 然后再插入.
 */
public class H_Problem324 {
    public static void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int[] res = new int[len];
        int mid = (1 + len) / 2;
        int index = 0;
        int left = mid - 1;
        int right = len - 1;
        // 降序插入
        while (right>=mid && left >= 0) {
            res[index++] = nums[left--];
            res[index++] = nums[right--];
        }
        if (left >= 0) {
            res[index] = nums[left];
        }
        if (right >= mid) {
            res[index] = nums[right];
        }
        System.arraycopy(res, 0, nums, 0, len);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    private static void partition(int[] nums, int k) {
        int left = -1;
        int right = nums.length - 1;
        int index = 0;
        while (index < right) {
            if (nums[index] < k) {
                swap(nums, index, ++left);
                index++;
            } else if (nums[left] > k) {
                swap(nums, index, right--);
            } else {
                index++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 3, 2, 3, 4, 5, 6};
        partition(nums, 3);
        System.out.print(Arrays.toString(nums));
    }
}
