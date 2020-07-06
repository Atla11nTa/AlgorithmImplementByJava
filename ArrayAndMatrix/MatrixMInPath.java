package ArrayAndMatrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目： 求最短通路值
 * 题目描述：矩阵中，1表示可走，0表示不可走，求从左上角到右下角的最短路径。
 * 思路分析： 图问题求两点间最短路径数值，用广度优先遍历。
 * 每次从四个方向选择下一坐标点，注意使用map矩阵记录到每个坐标的路径距离，主要方便检查是否已经访问过了。
 */

public class MatrixMInPath {

    //检查下一坐标是否可走
    public static boolean isValid(int[][] matrix, int row, int col, int[][] map) {
        Integer[] pos = new Integer[]{row, col};
        //范围不在地图之间的情况
        if (!(pos[0] >= 0 && pos[0] < matrix.length && pos[1] >= 0 && pos[1] < matrix[0].length)) {
            return false;
        }
        //可达且没有访问过。
        return matrix[pos[0]][pos[1]] == 1 && map[pos[0]][pos[1]] == 0;
    }

    //起点左上角，终点右下角
    public static int solution(int[][] matrix) {
        Queue<Integer[]> queue = new LinkedList<>();
        //记录到达每个坐标的路径长度
        int[][] map = new int[matrix.length][matrix[0].length];
        int curRow,curCol,nextRow,nextCol;
        map[0][0] = 1;
        queue.offer(new Integer[]{0, 0});
        while (!queue.isEmpty()) {
            Integer[] temp = queue.poll();
            curRow = temp[0];
            curCol = temp[1];
            if (curRow == matrix.length - 1 && curCol == matrix[0].length - 1) {
                return map[curRow][curCol];
            }
            //往上走
            nextRow = curRow-1;
            nextCol = curCol;
            if (isValid(matrix, nextRow, nextCol, map)) {
                queue.offer(new Integer[]{nextRow, nextCol});
                map[nextRow][nextCol] = map[curRow][curCol] + 1;
            }
            //往下走
            nextRow = curRow+1;
            nextCol = curCol;
            if (isValid(matrix, nextRow, nextCol, map)) {
                queue.offer(new Integer[]{nextRow, nextCol});
                map[nextRow][nextCol] = map[curRow][curCol] + 1;
            }
            //往左走
            nextRow = curRow;
            nextCol = curCol - 1;
            if (isValid(matrix, nextRow, nextCol, map)) {
                queue.offer(new Integer[]{nextRow, nextCol});
                map[nextRow][nextCol] = map[curRow][curCol] + 1;
            }
            //往右走
            nextRow = curRow;
            nextCol = curCol + 1;
            if (isValid(matrix, nextRow, nextCol, map)) {
                queue.offer(new Integer[]{nextRow, nextCol});
                map[nextRow][nextCol] = map[curRow][curCol] + 1;
            }
        }
        //不可达
        return 0;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 1, 1, 1}
        };
        System.out.println(solution(matrix));
    }
}
