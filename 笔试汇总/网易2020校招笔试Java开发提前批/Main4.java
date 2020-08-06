package 笔试汇总.网易2020校招笔试Java开发提前批;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目：对于一组数字，每次输入一个数字num，将数组中大于等于num的所有数字减一。
 * 思路： 先排序，然后逆序遍历，如果大于等于目标，就减1, 最终还是一个有序的数组。
 * 比如数组是 1,2,3,3,4. 目标是3
 * 然后更新后是 1,2,2,2,3， 仍然有序。
 */
public class Main4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        Arrays.sort(nums);
        for (int i = 0; i < m; i++) {
            int checkNum = in.nextInt();
            int j = n - 1;
            for (; j >= 0; j--) {
                if (nums[j] >= checkNum) {
                    nums[j]--;
                } else {
                    break;
                }
            }
            System.out.println(n - j - 1);
        }
    }
}
