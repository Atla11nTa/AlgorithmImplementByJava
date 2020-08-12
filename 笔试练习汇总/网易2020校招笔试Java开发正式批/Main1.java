package 笔试练习汇总.网易2020校招笔试Java开发正式批;

import java.util.Scanner;

/**
 * 题目： 找一个最小的数字N，使N的各位之和相加大于X
 * 贪心思路
 */
public class Main1 {
    static int[] res = new int[100000];

    private static int findMin(int num) {
        int curSum = 0;
        int index = 0;
        while (curSum != num) {
            int cur = 9;
            if (cur + curSum > num) {
                cur = num - curSum;
            }
            curSum += cur;
            res[index++] = cur;
        }
        return index;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            int index = findMin(num);
            for (int j = index - 1; j >= 0; j--) {
                System.out.print(res[j]);
            }
            System.out.println();
        }
    }
}
