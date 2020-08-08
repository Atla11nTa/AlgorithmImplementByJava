package 笔试汇总.网易;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] nums = new int[m];
        int[] res = new int[n];
        for (int i = 0; i < m; i++) {
            nums[i] = in.nextInt();
        }
        boolean[] hash = new boolean[n + 1];
        for (int i = 0; i < m; i++) {
            if (nums[i] <= n) {
                hash[nums[i]] = true;
            }
        }
        int index1 = 0;
        int num1;
        int pre = 1;
        for (int i = 0; i < n; i++) {
            num1 = -1;
            int j = pre;
            // 找到一个没用过的
            for (; j < hash.length; j++) {
                if (!hash[j]) {
                    num1 = j;
                    break;
                }
            }
            pre = j;
            if (index1 >= m) {
                hash[j] = true;
                res[i] = num1;
            } else if (j >= n + 1) {
                res[i] = nums[index1];
                index1++;
            } else {
                int num2 = nums[index1];
                if (num1 < num2) {
                    hash[j] = true;
                    res[i] = num1;
                } else {
                    res[i] = num2;
                    index1++;
                }
            }
        }
        for (int i = 0; i < n - 1; i++) {
            System.out.print(res[i]+" ");
        }
        System.out.print(res[n - 1]);
    }
}
