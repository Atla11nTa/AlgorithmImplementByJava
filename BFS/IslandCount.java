package BFS;

import java.util.*;

/**
 * 题目：岛屿数量
 * leetCode地址：https://leetcode-cn.com/problems/number-of-islands/
 * 思路分析： 每次从一个为'1'的坐标开始进行BFS，遍历所有为1的点，并且将已访问的坐标标记，完成一次BFS即遍历了一个岛屿。然后接着从未访问的为'1'
 * 的坐标开始遍历。
 * 注意点： 广度优先遍历是在加入队列时标记已访问，而不是在弹出队列时标记。
 */
public class IslandCount {
    public static void isCan(char[][] gird, boolean[][] isVisited, Queue<Integer[]> queue, int x, int y) {
        //超过边界
        if (x < 0 || x >= gird.length || y < 0 || y >= gird[0].length) {
            return;
        }
        //是水
        if (gird[x][y] == '0') {
            return;
        }
        // 已在集中
        if (isVisited[x][y]) {
            return;
        }
        queue.offer(new Integer[]{x, y});
        isVisited[x][y] = true;
    }

    public static void Bfs(char[][] gird, boolean[][] isVisited, int beginX, int beginY) {
        Queue<Integer[]> queue = new LinkedList<>();
        Integer[] beginCor = new Integer[]{beginX, beginY};
        queue.offer(beginCor);
        isVisited[beginX][beginY] = true;
        while (!queue.isEmpty()) {
            Integer[] curCord = queue.poll();
            //上一个点
            isCan(gird, isVisited, queue, curCord[0] - 1, curCord[1]);
            //下一个点
            isCan(gird, isVisited, queue, curCord[0] + 1, curCord[1]);
            //左一个点
            isCan(gird, isVisited, queue, curCord[0], curCord[1] - 1);
            //右一个点
            isCan(gird, isVisited, queue, curCord[0], curCord[1] + 1);
        }
    }
    public static int solution(char[][] gird) {
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
    public static void main(String[] args) {
        char[][] gird = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(solution(gird));
    }
}
