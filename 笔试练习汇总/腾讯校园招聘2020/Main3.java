package 笔试练习汇总.腾讯校园招聘2020;

import java.util.Arrays;
import java.util.Scanner;

public class Main3 {

    private static void reverse(int[] nums, int begin, int end) {
        int mid = begin + (end - begin) / 2;
        int index = 0;
        for (int i = begin; i <= mid; i++) {
            int temp = nums[i];
            nums[i] = nums[end - index];
            nums[end - index] = temp;
            index++;
        }
    }
    // 每n个翻转一次
    private static void reverse(int[] nums, int n) {
        int index = 0;
        while (index < nums.length) {
            reverse(nums, index, Math.min(index + n - 1, nums.length - 1));
            index = index + n;
        }
    }

    private static int getRes(int[] nums) {
        int res = 0;
        int len = nums.length;
        int count;
        for (int i = 0; i < len; i++) {
            count = 0;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[i]) {
                    count++;
                }
            }
            res += count;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[1 << n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] op = new int[m];
        for (int i = 0; i < m; i++) {
            op[i] = 1 << in.nextInt();
        }
        for (int i = 0; i < m; i++) {
            reverse(nums, op[i]);
            System.out.println(getRes(nums));
        }
    }
}
