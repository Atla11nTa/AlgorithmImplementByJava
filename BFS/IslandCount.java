package BFS;

import java.util.*;

/**
 * 题目：岛屿数量
 * leetCode地址：https://leetcode-cn.com/problems/number-of-islands/
 * 思路分析： 每次从一个为'1'的坐标开始进行BFS，遍历所有为1的点，并且将已访问的坐标标记，完成一次BFS即遍历了一个岛屿。然后接着从未访问的为'1'
 * 的坐标开始遍历。
 * 注意点： 1. 思路是简单的广度优先遍历，但是通常在找下一个点时，会先判断，是有效点才入队，但是对周围四个点分别进行一次判断，会导致超时。
 *  解决办法是加入时不判断，把周围四个点都先加入，而在访问时判断，逻辑会有点不同。
 */
public class IslandCount {
    public static void Bfs(char[][] gird, boolean[][] isvisited, int beginX, int beginY) {
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
