package 笔试汇总.同城58;

import java.util.Scanner;

public class Main1 {
    static int[][] nexts = {
            {-1, 0}, {1, 0}, {0, 1}, {0, -1}
    };

    public static void dfs(int[][] matrix, int startX, int startY) {
        int nextX, nextY;
        int n = matrix.length;
        int m = matrix[0].length;
        for (int[] next : nexts) {
            nextX = next[0] + startX;
            nextY = next[1] + startY;
            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < m && matrix[nextX][nextY] == 1) {
                matrix[nextX][nextY] = 0;
                dfs(matrix, nextX, nextY);
            }
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] != 0) {
                    matrix[i][j] = 0;
                    dfs(matrix, i, j);
                    count++;
                }
            }
        }
        System.out.print(count);
    }
}
