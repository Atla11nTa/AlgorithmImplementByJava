package LeetCode;

import java.util.Arrays;

/**
 * 题目: 329. 矩阵中的最长递增路径
 * 地址:https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/
 * 思路: 带记忆的回溯法, 普通的回溯法有大量的重复计算.所以用一个memory数组存储计算过程中的答案.
 *  DFS函数的返回以start为起点的最长路径长度, 用memory数组存储DFS函数的中间结果, 避免重复计算.
 */
public class H_Problem329 {
    // 快速找next
    private int[][] nextMap = new int[][]{
            {-1, 0}, {0, -1}, {1, 0}, {0, 1}
    };

    int Row, Col;
    // 以start开始找最长的递增路径长度
    private int backTrack(int[][] matrix, int[][] memory, int startX, int startY) {
        // 如果有答案, 说明已经以该节点开始计算过了.
        if (memory[startX][startY] != 0) {
            return memory[startX][startY];
        }
        int nextX, nextY;
        memory[startX][startY]++;
        for (var next:nextMap) {
            nextX = startX + next[0];
            nextY = startY + next[1];
            if (nextX >= 0 && nextX < Row && nextY >= 0 && nextY < Col && matrix[nextX][nextY] > matrix[startX][startY]) {
                // 下一节点的最长路径长度+1 的最大值就是以本节点为起点的答案
                memory[startX][startY] = Math.max(memory[startX][startY], backTrack(matrix, memory, nextX, nextY) + 1);
            }
        }
        return memory[startX][startY];
    }

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        Row = matrix.length;
        Col = matrix[0].length;
        int max = 0;
        // 记录计算过程中的答案
        int[][] memory = new int[Row][Col];
        for (int i = 0; i < Row; i++) {
            for (int j = 0; j < Col; j++) {
                int temp = backTrack(matrix, memory, i, j);
                max = Math.max(temp, max);
            }
        }
        return max;
    }
}
