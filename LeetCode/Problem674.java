package LeetCode;

/**
 * 题目： 674. 最长连续递增序列
 * 地址： https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/
 * 思路： 遍历即可
 */
public class Problem674 {
    public static int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int curLen = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                curLen++;
            } else {
                max = Math.max(max, curLen);
                curLen = 1;
            }
        }
        return Math.max(curLen, max);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2};
        System.out.println(findLengthOfLCIS(nums));
    }
}
