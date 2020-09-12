package 笔试汇总.科大讯飞;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] strs = in.next().split(",");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(strs[i]);
        }
        int minIndex;
        for (int i = 0; i < n - 1; i++) {
            minIndex = i;
            for (int j = i; j < n; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;
        }
        for (int i = 0; i < n - 1; i++) {
            System.out.print(nums[i]+",");
        }
        System.out.print(nums[n - 1]);
    }
}
