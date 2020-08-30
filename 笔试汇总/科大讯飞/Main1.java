package 笔试汇总.科大讯飞;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] input = in.next().split(",");
        int row = Integer.parseInt(input[0]);
        int col = Integer.parseInt(input[1]);
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        int[] dp = new int[col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (j == 0) {
                    dp[j] = dp[j] + matrix[i][j];
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]) + matrix[i][j];
                }
            }
        }
        System.out.print(dp[col-1]);
    }
}
