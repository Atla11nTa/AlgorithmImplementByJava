package 笔试练习汇总.网易2020校招笔试Java开发正式批;

import java.util.Scanner;

/**
 * 题目： 二种选择的问题。
 * 递归解答，注意结束条件。
 */

public class Main2 {
    static int min = Integer.MAX_VALUE;
    static boolean findRes = false;

    private static void getMin(int A, int B, int P, int Q, int op) {
        // 如果已经找到了一个答案
        if (findRes && op > min) {
            return;
        }
        if (B <= A) {
            findRes = true;
            min = Math.min(min, op);
            return;
        }
        getMin(A + P, B, P, Q, op + 1);
        getMin(A, B, P * Q, Q, op + 1);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int A = in.nextInt();
            int B = in.nextInt();
            int P = in.nextInt();
            int Q = in.nextInt();
            min = Integer.MAX_VALUE;
            findRes = false;
            getMin(A, B, P, Q, 0);
            System.out.println(min);
        }
    }
}
