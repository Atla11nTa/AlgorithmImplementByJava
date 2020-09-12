package 笔试练习汇总.腾讯校园招聘2020;

import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 题目：每天可以选择工作/健身/休息，不能连续两天工作或者健身，求至少休息多少天。
 * 思路： 状态转移DP
 * dp[i][0]表示第i天选择休息的最少休息天数
 * dp[i][1]表示第i天选择健身的最少休息天数
 * dp[i][2]表示第i选择工作的最少休息天数
 * 画出状态转移图。根据状态转移图不断更新
 */


public class Main4 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] company = new int[n];
        int[] gym = new int[n];
        for (int i = 0; i < n; i++) {
            company[i] = in.nextInt();

        }
        for (int i = 0; i < n; i++) {
            gym[i] = in.nextInt();
        }
        int[][] dp = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            // 可以锻炼
            if (gym[i - 1] == 1) {
                // 锻炼的状态从休息或者工作转移而来。
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]);
            }
            // 不可以锻炼
            else {
                dp[i][1] = Integer.MAX_VALUE;
            }
            // 可以工作
            if (company[i - 1] == 1) {
                // 工作的状态从锻炼或者休息转移而来。
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]);
            }
            // 不可以工作
            else{
                dp[i][2] = Integer.MAX_VALUE;
            }
            // 休息的状态从三个转移而来。
            dp[i][0] = Math.min(dp[i - 1][0], Math.min(dp[i - 1][1], dp[i - 1][2])) + 1;
        }
        int res = Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2]));
        System.out.println(res);
    }
}
