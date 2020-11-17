package 笔试汇总.同城58;

import java.util.concurrent.ThreadPoolExecutor;

public class Main3 {
    public int firstMissingPositive (int[] nums) {
        // write code here
        int n = nums.length;
        boolean[] hash = new boolean[n + 2];
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0 && nums[i] <= n) {
                hash[nums[i]] = true;
            }
        }
        int res = 1;
        for (int i = 1; i < n + 1; i++) {
            if (!hash[i]) {
                res = i;
                break;
            }
        }
        return res;
    }
}
