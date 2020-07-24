package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目:73. 矩阵置零
 */

public class Problem73 {
    private void matrixSetZero(int[][] matrix, int i, int j) {
        int Row = matrix.length;
        int Col = matrix[0].length;
        for (int col = 0; col < Col; col++) {
            matrix[i][col] = 0;
        }
        for (int row = 0; row < Row; row++) {
            matrix[row][j] = 0;
        }
    }

    public void setZeroes(int[][] matrix) {
        List<Integer[]> list = new ArrayList<>();
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    list.add(new Integer[]{i, j});
                }
            }
        }
        for (var iJ : list) {
            matrixSetZero(matrix, iJ[0], iJ[1]);
        }
    }
}
