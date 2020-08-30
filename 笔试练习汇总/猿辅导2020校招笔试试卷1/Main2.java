package 笔试练习汇总.猿辅导2020校招笔试试卷1;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main2 {
    static int[][] nexts = new int[][]{
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    public static int[][][] memo;
    static int row;
    static int col;
    public static int traverse(int[][] matrix, int x, int y, int k) {
        if (memo[x][y][k] != 0) {
            return memo[x][y][k];
        }
        int res = 1;
        for (int[] next : nexts) {
            int nextX = x + next[0];
            int nextY = y + next[1];
            if (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col) {
                if (matrix[nextX][nextY] > matrix[x][y]) {
                    res = Math.max(traverse(matrix, nextX, nextY, k) + 1, res);
                } else if (k > 0) {
                    res = Math.max(traverse(matrix, nextX, nextY, k - 1) + 1, res);
                }
            }
        }
        memo[x][y][k] = res;
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        row = in.nextInt();
        col = in.nextInt();
        int k = in.nextInt();
        int[][] matrix = new int[row][col];
        memo = new int[row][col][11];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res = Math.max(res, traverse(matrix, i, j, k));
            }
        }
        System.out.println(res);
    }
}
