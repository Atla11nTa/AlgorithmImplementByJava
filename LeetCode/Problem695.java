package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：695.岛屿的最大面积
 * 链接： https://leetcode-cn.com/problems/max-area-of-island/
 * 思路：深度优先搜索。
 */
public class Problem695 {
    public static int isCan(int[][] grid, boolean[][] visitedMap, int x, int y,Queue<Integer[]> queue) {
        if (x < 0 || y < 0 || x > grid.length - 1 || y > grid[0].length - 1) {
            return 0;
        }
        if (visitedMap[x][y] || grid[x][y] == 0) {
            return 0;
        }
        queue.offer(new Integer[]{x, y});
        visitedMap[x][y] = true;
        return 1;
    }
    public static int BFS(int[][] grid, boolean[][] visitedMap, int x, int y) {
        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[]{x, y});
        visitedMap[x][y] = true;
        Integer[] currentPos;
        int nextX;
        int nextY;
        int isLandSize = 1;
        while (!queue.isEmpty()) {
            currentPos = queue.poll();
            //上一个点
            isLandSize += isCan(grid, visitedMap, currentPos[0] - 1, currentPos[1], queue);
            isLandSize += isCan(grid, visitedMap, currentPos[0] + 1, currentPos[1], queue);
            isLandSize += isCan(grid, visitedMap, currentPos[0], currentPos[1] - 1, queue);
            isLandSize += isCan(grid, visitedMap, currentPos[0], currentPos[1] + 1, queue);
        }
        return isLandSize;
    }
    public static int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        boolean[][] visitedMap = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0;j < grid[0].length; j++) {
                //从一个未访问过的开始
                if (grid[i][j] == 1&&!visitedMap[i][j]) {
                    max = Math.max(BFS(grid, visitedMap, i, j), max);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0, 1, 0, 0},
                {1, 0, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0}
        };

        System.out.println(maxAreaOfIsland(grid));
    }
}
