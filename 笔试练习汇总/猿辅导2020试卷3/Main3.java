package 笔试练习汇总.猿辅导2020试卷3;

import java.util.Scanner;

public class Main3 {
    public static void solution(int[] nums, int s) {
        int maxLen = Integer.MIN_VALUE;
        int left = 0;
        int right = 0;
        int curLen = 0;
        int curSum = 0;
        int n = nums.length;
        while (right < n) {
            if (curSum + nums[right] <= s) {
                curSum += nums[right];
                right++;
            } else {
                curLen = right - left;
                maxLen = Math.max(curLen, maxLen);
                curSum -= nums[left];
                left++;
            }
        }
        while (curSum > s && left < right) {
            curSum -= nums[left];
            left++;
        }
        curLen = right - left;
        maxLen = Math.max(curLen, maxLen);
        System.out.print(maxLen);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int s = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        solution(nums, s);
    }
}
