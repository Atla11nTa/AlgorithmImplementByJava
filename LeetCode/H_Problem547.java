package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：547. 朋友圈
 * 地址： https://leetcode-cn.com/problems/friend-circles/submissions/
 * 思路：理解题意，map矩阵其实就是邻接矩阵，就是求连通图的个数，每个连通图就是一个朋友圈。
 * DFS或者BFS皆可，时间复杂度O(N^2)，也可以用并查集做。
 */

public class H_Problem547 {
    void DFS(int[][] map, boolean[] isVisited, int start) {
        for (int next = 0; next < map.length; next++) {
            if (map[start][next] == 1 && !isVisited[next]) {
                isVisited[next] = true;
                DFS(map, isVisited, next);
            }
        }
    }
    void Bfs(int[][] map, boolean[] isVisited, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        isVisited[start] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next = 0; next < map.length; next++) {
                if (!isVisited[next] && map[cur][next] == 1) {
                    queue.offer(next);
                    isVisited[next] = true;
                }
            }
        }
    }
    private int findCircleNum(int[][] map) {
        boolean[] isVisited = new boolean[map.length];
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            if (!isVisited[i]) {
                count++;
                DFS(map, isVisited, i);
            }
        }
        return count;
    }
}
