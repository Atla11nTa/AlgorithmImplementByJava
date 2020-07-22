package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目:54.打印螺旋数组
 * 思路: 仅一行一列的单独处理,其他的旋转打印.
 */
public class Problem54 {
    private void getOneCircle(int[][] matrix, List<Integer> resList, int[] leftUp, int[] rightUp, int[] leftDown, int[] rightDown) {
        int i = leftUp[0];
        int j = leftUp[1];
        //只有一行
        if (leftUp[0] == leftDown[0]) {
            while (j <= rightUp[1]) {
                resList.add(matrix[i][j]);
                j++;
            }
        }
        // 只有一列
        else if (leftUp[1] == rightUp[1]) {
            while (i <= rightDown[0]) {
                resList.add(matrix[i][j]);
                i++;
            }
        } else {
            // 从左打印到右
            while (j>=leftUp[1] && j < rightUp[1]) {
                resList.add(matrix[i][j]);
                j++;
            }
            // 从上打印到下
            while (i>=leftUp[0] && i < rightDown[0]) {
                resList.add(matrix[i][j]);
                i++;
            }
            // 从右打印到左
            while (j <= rightDown[1] && j > leftDown[1]) {
                resList.add(matrix[i][j]);
                j--;
            }
            // 从下打印到上
            while (i <= rightDown[0] && i > leftUp[0]) {
                resList.add(matrix[i][j]);
                i--;
            }
        }
    }
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> resList = new ArrayList<>();
        // 左上角
        int[] leftUp = new int[]{0, 0};
        //右上角
        int[] rightUp = new int[]{0, matrix[0].length - 1};
        // 左下角
        int[] leftDown = new int[]{matrix.length - 1, 0};
        // 右下角
        int[] rightDown = new int[]{matrix.length - 1, matrix[0].length - 1};
        while (leftUp[0] <= rightDown[0] && leftUp[1] <= rightDown[1]) {
            getOneCircle(matrix, resList, leftUp, rightUp, leftDown, rightDown);
            // 四个角向中间移动
            leftUp[0]++;
            leftUp[1]++;

            rightUp[0]++;
            rightUp[1]--;

            leftDown[0]--;
            leftDown[1]++;

            rightDown[0]--;
            rightDown[1]--;
        }
        return resList;
    }
}
