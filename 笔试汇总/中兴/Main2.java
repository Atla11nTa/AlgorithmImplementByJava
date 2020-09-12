package 笔试汇总.中兴;

import java.util.Scanner;

public class Main2 {
    // 找最长递增子序列
    public static int getRes(int[] nums, int n) {
        int[] dpUp = new int[n];
        int[] dpDown = new int[n];
        dpUp[0] = 1;
        dpDown[0] = 1;
        int resUp = 0;
        int resDown = 0;
        for (int i = 1; i < n; i++) {
            int maxUp = 1;
            int maxDown = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxUp = Math.max(maxUp, dpUp[j] + 1);
                } else if (nums[i] < nums[j]) {
                    maxDown = Math.max(maxDown, dpDown[j] + 1);
                }
            }
            dpUp[i] = maxUp;
            dpDown[i] = maxDown;
            resUp = Math.max(dpUp[i], resUp);
            resDown = Math.max(dpDown[i], resDown);
        }
        return Math.min(n - resUp, n - resDown);
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
            System.out.println(getRes(nums, n));
        }
    }
}
