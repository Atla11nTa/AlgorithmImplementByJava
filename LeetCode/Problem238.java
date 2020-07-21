package LeetCode;

import java.util.HashSet;

public class Problem238 {
    public int[] productExceptSelf(int[] nums) {
        int[] pre = new int[nums.length];
        int[] next = new int[nums.length];
        pre[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            pre[i] = pre[i - 1] * nums[i - 1];
        }
        next[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            next[i] = next[i + 1] * nums[i + 1];
        }
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = pre[i] * next[i];
        }
        return res;
    }
}
