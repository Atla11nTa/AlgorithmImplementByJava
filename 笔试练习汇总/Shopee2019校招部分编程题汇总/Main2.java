package 笔试练习汇总.Shopee2019校招部分编程题汇总;

import java.util.Scanner;

public class Main2 {
    public static boolean check(int[] nums, long perLen, int timeLimit) {
        int time = 0;
        int n = nums.length;
        long cur = perLen;
        if (cur >= nums[0]) {
            cur -= nums[0];
            time++;
        } else {
            return false;
        }
        for (int i = 1; i < n; i++) {
            if (cur >= nums[i]) {
                cur -= nums[i];
            } else {
                cur = perLen;
                if (cur < nums[i]) {
                    return false;
                }
                cur -= nums[i];
                time++;
            }
        }
        return time <= timeLimit;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        check(nums, 8, m);
        long left = 1;
        long right = Long.MAX_VALUE-100;
        long mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (check(nums, mid, m)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(right);
    }
}
