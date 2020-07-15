package LeetCode;

import java.util.Arrays;

/**
 * 题目：48.旋转图像
 */
public class Problem48 {
    public static void solution(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        //先按水平对折
        for (int i = 0; i < row / 2; i++) {
            for (int j = 0; j < col; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[row - i - 1][j];
                matrix[row - i - 1][j] = temp;
            }
        }
        //再按对角线对折
        for (int i = 0; i < row; i++) {
            for (int j = i + 1; j < col; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        solution(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
