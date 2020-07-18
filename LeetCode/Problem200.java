package LeetCode;

import java.util.*;

/**
 * 题目：岛屿数量
 * leetCode地址：https://leetcode-cn.com/problems/number-of-islands/
 * 思路分析： 每次从一个为'1'的坐标开始进行BFS，遍历所有为1的点，并且将已访问的坐标标记，完成一次BFS即遍历了一个岛屿。然后接着从未访问的为'1'
 * 的坐标开始遍历。
 * 注意点： 广度优先遍历是在加入队列时标记已访问，而不是在弹出队列时标记。
 */
public class Problem200 {
    public void Bfs(char[][] gird, boolean[][] isvisited, int beginX, int beginY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{beginX, beginY});
        int x = 0, y = 0;
        while (!queue.isEmpty()) {
            int[] curCord = queue.poll();
            x = curCord[0];
            y = curCord[1];
            if (x >= 0 && x < gird.length && y >= 0 && y < gird[0].length && gird[x][y] == '1' && !isvisited[x][y]) {
                isvisited[x][y] = true;
                queue.offer(new int[]{x - 1, y});
                queue.offer(new int[]{x + 1, y});
                queue.offer(new int[]{x, y + 1});
                queue.offer(new int[]{x, y - 1});
            }
        }
    }
    public int numIslands(char[][] gird) {
        if (gird == null || gird.length == 0) {
            return 0;
        }
        int res = 0;
        boolean[][] isVisited = new boolean[gird.length][gird[0].length];
        for (int i = 0; i < gird.length; i++) {
            for (int j = 0; j < gird[0].length; j++) {
                if (gird[i][j] == '1' && !isVisited[i][j]) {
                    Bfs(gird, isVisited, i, j);
                    res++;
                }
            }
        }
        return res;
    }
}
