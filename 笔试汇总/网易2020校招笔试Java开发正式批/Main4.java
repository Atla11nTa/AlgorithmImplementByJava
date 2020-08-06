package 笔试汇总.网易2020校招笔试Java开发正式批;

import java.util.Scanner;

/**
 * 滑动窗口问题，求一个最长的连续子序列。
 */

public class Main4 {
    public static int longestPerfect(int[] nums, int m) {
        int res = 1;
        int left = 0, right = 0;
        int curSum = 0;
        while (right < m) {
            // 如果还是比前面的和大，right继续移动
            if (nums[right] >= curSum) {
                curSum += nums[right];
                right++;
            }
            // 缩进left
            else {
                res = Math.max(res, right - left);
                while (nums[right] < curSum) {
                    curSum -= nums[left];
                    left++;
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int m = in.nextInt();
            int[] nums = new int[m];
            for (int j = 0; j < m; j++) {
                nums[j] = in.nextInt();
            }
            System.out.println(longestPerfect(nums, m));
        }
    }
}
