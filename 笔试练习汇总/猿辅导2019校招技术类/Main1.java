package 笔试练习汇总.猿辅导2019校招技术类;

import java.util.Map;
import java.util.Scanner;

public class Main1 {
    private static void reverse(int[] nums, int begin, int end) {
        int mid = begin + (end - begin) / 2;
        int temp;
        int index = 0;
        for (int i = begin; i <= mid; i++) {
            temp = nums[i];
            nums[i] = nums[end - index];
            nums[end - index] = temp;
            index++;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int begin = 0;
        while (begin < n) {
            reverse(nums, begin, Math.min(begin + m - 1, n - 1));
            begin = begin + m;
        }
        reverse(nums, 0, n - 1);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
