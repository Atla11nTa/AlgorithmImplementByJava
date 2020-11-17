package 笔试汇总.京东;

import java.util.Scanner;

public class Main2 {
    static int[][] nexts = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    public static boolean dfs(char[][] matrix, int startX, int startY) {
        int nextX, nextY;
        int row = matrix.length;
        int col = matrix[0].length;
        boolean res = false;
        for (int[] next : nexts) {
            nextX = next[0] + startX;
            nextY = next[1] + startY;
            if (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col) {
                if (matrix[nextX][nextY] == 'E') {
                    return true;
                } else if (matrix[nextX][nextY] == '.') {
                    matrix[nextX][nextY] = '#';
                    res = dfs(matrix, nextX, nextY) || res;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseNum = in.nextInt();
        for (int i = 0; i < caseNum; i++) {
            int row = in.nextInt();
            int col = in.nextInt();
            char[][] matrix = new char[row][];
            for (int j = 0; j < row; j++) {
                matrix[j] = in.next().toCharArray();
            }
            int startX = 0;
            int startY = 0;
            for (int j = 0; j < row; j++) {
                for (int k = 0; k < col; k++) {
                    if (matrix[j][k] == 'S') {
                        startX = j;
                        startY = k;
                    }
                }
            }
            System.out.println(dfs(matrix, startX, startY) ? "YES" : "NO");
        }

    }
}
