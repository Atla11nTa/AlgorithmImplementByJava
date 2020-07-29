package LeetCode;

import java.util.Arrays;

/**
 * 题目： 31. 下一个排列
 * 地址： https://leetcode-cn.com/problems/next-permutation/
 * 思路： 字典序下一个更大的排序， 主要时找降序点， 从右往左找第一个降序点i， 降序点右侧i-1到len-1这段是严格降序的, 然后从降序点往右找最后一个
 * 比降序点大的数，把这个数与降序点位置的数交换就让这个排序更大，为了是下一个排列，因为i右侧还是降序， 改成升序就是极小的排列了。
 */

public class Problem31 {
    public static void nextPermutation(int[] nums) {
        int len = nums.length;
        int decsIndex = len - 2;
        while (decsIndex >= 0 && nums[decsIndex] >= nums[decsIndex + 1]) {
            decsIndex--;
        }
        if (decsIndex >= 0) {
            int j = decsIndex + 1;
            while (j < len && nums[j] > nums[decsIndex]) {
                j++;
            }
            int temp = nums[j - 1];
            nums[j - 1] = nums[decsIndex];
            nums[decsIndex] = temp;
        }
        reverse(nums, decsIndex + 1, len - 1);
    }

    private static void reverse(int[] nums, int begin, int end) {
        int mid = (begin + end) / 2;
        int temp;
        int index = 0;
        for (int i = begin; i <= mid; i++) {
            temp = nums[i];
            nums[i] = nums[end - index];
            nums[end - index] = temp;
            index++;
        }
    }

    public static void main(String[] args) {
        int[] test = new int[]{3, 2, 1};
        nextPermutation(test);
        System.out.println(Arrays.toString(test));
    }
}
