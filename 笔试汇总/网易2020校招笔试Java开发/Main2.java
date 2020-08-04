package 笔试汇总.网易2020校招笔试Java开发;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 思路：先排序，前面都是满足要求的，仅最后一个数需要考虑，若最后一个数比相邻两个数小，那肯定满足要求，若不满足，就把最后一个数与倒数第二个数交换，
 * 然后检查是否满足，满足即得到答案。
 */

public class Main2 {
    private static boolean check(long[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        Arrays.sort(nums);
        // 最后一个数比相邻两个数大，进一步考察。
        if (nums[n - 1] >= nums[n - 2] + nums[0]) {
            return nums[n - 1] < nums[n - 3] + nums[n - 2];
        } else {
            return true;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        boolean[] res = new boolean[t];
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            long[] nums = new long[n];
            for (int j = 0; j < n; j++) {
                nums[j] = in.nextLong();
            }
            res[i] = check(nums);
        }
        for (boolean r : res) {
            System.out.println(r ? "YES" : "NO");
        }
    }
}
