package LeetCode;

/**
 * 136. 只出现一次的数字
 * 思路:异或.
 */

public class Problem136 {
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }
}
