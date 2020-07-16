package LeetCode;

import java.util.Arrays;

/**
 * 题目: 724.寻找数组的中心索引
 */
public class Problem724 {
    public static int solution(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        int leftSum = 0;
        int rightSum = sum - nums[0];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == rightSum) {
                index = i;
                break;
            }
            leftSum += nums[i];
            if (i + 1 < nums.length) {
                rightSum -= nums[i + 1];
            } else {
                return -1;
            }
        }
        return index;
    }
}
