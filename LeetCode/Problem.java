package LeetCode;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Problem {
    int[][] nexts = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    int row;
    int col;
    public int dfs(int[][] grid, int x, int y) {
        int nextX;
        int nextY;
        int res = 1;
        for (int i = 0; i < row; i++) {
            if (i != x) {
                if (grid[i][y] == 1) {
                    grid[i][y] = 0;
                    res += dfs(grid, i, y);
                }
            }
        }
        for (int i = 0; i < col; i++) {
            if (i != y) {
                if (grid[x][i] == 1) {
                    grid[x][i] = 0;
                    res += dfs(grid, x, i);
                }
            }
        }
        return res;
    }

    public int countServers(int[][] grid) {
        this.row = grid.length;
        this.col = grid[0].length;
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    int temp = dfs(grid, i, j);
                    res += temp > 1 ? temp : 0;
                }
            }
        }
        return res;
    }
}
