package 笔试汇总.猿辅导;

import java.util.Scanner;

public class Main2 {
    public static int maxSum(int[][] matrix, int col) {
        int max = Integer.MIN_VALUE;
        int row = matrix.length;
        int cur;
        int[] s;
        for (int i = 0; i < row; i++) {
            for (int z = 0; z < col; z++) {
                s = new int[col];
                for (int j = i; j < row; j++) {
                    cur = 0;
                    for (int k = z; k < z + col; k++) {
                        s[k-z] += matrix[j][k];
                        cur += s[k-z];
                        max = Math.max(max, cur);
                        cur = cur < 0 ? 0 : cur;
                    }
                }
            }
        }
        return max;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();
        int[][] matrix = new int[row][2 * col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt();
                matrix[i][j + col] = matrix[i][j];
            }
        }
        System.out.print(maxSum(matrix, col));
    }
}
