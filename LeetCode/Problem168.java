package LeetCode;

/**
 * 题目:多数元素
 */
public class Problem168 {
    public int majorityElement(int[] nums) {
        int count = 1;
        int major = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == major) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                major = nums[i];
                count = 1;
            }
        }
        return major;
    }
}
