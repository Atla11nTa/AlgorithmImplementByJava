package 笔试汇总.VIVO;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main1 {
    static int[][] nexts = new int[][]{
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        char[][] matrix = new char[n][n];
        int startX = in.nextInt();
        int startY = in.nextInt();
        int endX = in.nextInt();
        int endY = in.nextInt();
        for (int i = 0; i < n; i++) {
            matrix[i] = in.next().toCharArray();
        }
        boolean[][] isVisited = new boolean[n][n];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startX, startY});
        isVisited[startX][startY] = true;
        int len = 0;
        int[] cur = null;
        int nextX, nextY;
        int toBeVisited = 1;
        int nextLevel = 0;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur[0] == endX && cur[1] == endY) {
                break;
            }
            for (int[] next : nexts) {
                nextX = next[0] + cur[0];
                nextY = next[1] + cur[1];
                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && !isVisited[nextX][nextY] && matrix[nextX][nextY] != '#' && matrix[nextX][nextY] != '@') {
                    isVisited[nextX][nextY] = true;
                    queue.offer(new int[]{nextX, nextY});
                    nextLevel++;
                }
            }
            toBeVisited--;
            if (toBeVisited == 0) {
                toBeVisited = nextLevel;
                nextLevel = 0;
                len++;
            }
        }
        if (cur[0] == endX && cur[1] == endY) {
            System.out.print(len);
        } else {
            System.out.print(-1);
        }
    }
}
