package LeetCode;

/**
 * 题目：53.最大子序和
 * 思路： 如果nums[i]加上当前子序和大于0，那么nums[i]就纳入结果中，如果小于等于0，那么当前子序和就不能是最大了。
 * 因为有可能单一数字就是最大子序和的情况，所以对每一个也考虑max
 */
public class Problem53 {
    public int maxSubArray(int[] nums) {
        int current = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            // 对后序找最大有贡献
            if (current + nums[i] > 0) {
                current += nums[i];
                max = Math.max(current, max);
            }
            // 以i结尾不可能是最大。
            else {
                current = 0;
            }
            // 有可能单一元素就是最大。
            max = Math.max(nums[i], max);
        }
        return max;
    }
}
