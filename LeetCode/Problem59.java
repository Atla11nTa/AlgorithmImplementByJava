package LeetCode;

/**
 * 题目: 59.螺旋矩阵II
 * 思路:记住单独考虑仅一行一列的情况.
 */
public class Problem59 {
    private int getOneCircle(int beginNum, int[][] resMatrix, int[] leftUp, int[] rightDown) {
        int i = leftUp[0];
        int j = leftUp[1];
        //只有一行
        if (leftUp[0] == rightDown[0]) {
            while (j <= rightDown[1]) {
                resMatrix[i][j] = beginNum++;
                j++;
            }
        }
        // 只有一列
        else if (leftUp[1] == rightDown[1]) {
            while (i <= rightDown[0]) {
                resMatrix[i][j] = beginNum++;
                i++;
            }
        } else {
            // 从左打印到右
            while (j>=leftUp[1] && j < rightDown[1]) {
                resMatrix[i][j] = beginNum++;
                j++;
            }
            // 从上打印到下
            while (i>=leftUp[0] && i < rightDown[0]) {
                resMatrix[i][j] = beginNum++;
                i++;
            }
            // 从右打印到左
            while (j <= rightDown[1] && j > leftUp[1]) {
                resMatrix[i][j] = beginNum++;
                j--;
            }
            // 从下打印到上
            while (i <= rightDown[0] && i > leftUp[0]) {
                resMatrix[i][j] = beginNum++;
                i--;
            }
        }
        return beginNum;
    }

    public int[][] generateMatrix(int n) {
        int[][] resMatrix = new int[n][n];
        int[] leftUp = new int[]{0, 0};
        int[] rightDown = new int[]{n - 1, n - 1};
        int beginNum = 1;
        while (leftUp[0] <= rightDown[0] && leftUp[1] <= rightDown[1]) {
            beginNum = getOneCircle(beginNum, resMatrix, leftUp, rightDown);
            leftUp[0]++;
            leftUp[1]++;
            rightDown[0]--;
            rightDown[1]--;
        }
        return resMatrix;
    }
}
