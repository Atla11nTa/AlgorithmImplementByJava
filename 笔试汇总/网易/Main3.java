package 笔试汇总.网易;

import java.util.Scanner;

public class Main3 {
    private static int res = Integer.MIN_VALUE;

    private static void backTrack(int[] nums, int a, int b, int start, int n) {
        if (a == b) {
            res = Math.max(res, a);
        }
        if (start == n) {
            return;
        }
        backTrack(nums, a + nums[start], b, start + 1, n);
        backTrack(nums, a, b + nums[start], start + 1, n);
        backTrack(nums, a, b, start + 1, n);
    }

    private static long getResult(int[] nums, int n) {
        res = Integer.MIN_VALUE;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        backTrack(nums, 0, 0, 0, n);
        return sum - res * 2;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseNum = in.nextInt();
        for (int i = 0; i < caseNum; i++) {
            int n = in.nextInt();
            int[] nums = new int[n];
            for (int j = 0; j < n; j++) {
                nums[j] = in.nextInt();
            }
            System.out.println(getResult(nums, n));
        }
    }
}
