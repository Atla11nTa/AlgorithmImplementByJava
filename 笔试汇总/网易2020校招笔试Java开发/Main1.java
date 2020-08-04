package 笔试汇总.网易2020校招笔试Java开发;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 思路：思路比较巧妙，先统计奇数和偶数的个数，若全是奇数或全是偶数，那就无法排序，只要存在一个奇数，那就可以进行交换排序。
 * 注意是任意交换的。不是相邻交换
 */

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int oddCount = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 != 0) {
                oddCount++;
            }
        }
        if (oddCount == 0 || oddCount == n) {
            Arrays.stream(nums).forEach((a) -> {
                System.out.print(a + " ");
            });
        } else {
            Arrays.sort(nums);
            for (int num : nums) {
                System.out.print(num + " ");
            }
        }
    }
}
