package LeetCode;

/**
 * 题目： 不同路径III
 * 链接：https://leetcode-cn.com/problems/unique-paths-iii/
 * 解题思路：典型回溯，相比普通的回溯找路径问题，本题在找路径时，若下一路径是终点，要求其他点必须全部已经访问。
 */
public class Problem980 {

    public static int uniquePathsIII(int[][] grid) {
        int blockCount = 0;
        int[] begin = new int[2];
        int[] end = new int[2];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == -1) {
                    blockCount++;
                }
                else if (grid[i][j] == 1) {
                    begin[0] = i;
                    begin[1] = j;
                } else if (grid[i][j] == 2) {
                    end[0] = i;
                    end[1] = j;
                }
            }
        }
        //要求的路径长度
        int pathLen = (grid.length) * (grid[0].length) - blockCount;
        boolean[][] isVisited = new boolean[grid.length][grid[0].length];
        isVisited[begin[0]][begin[1]] = true;
        return Dfs(begin, end, isVisited, grid, pathLen, 1);
    }

    public static int Dfs(int[] begin, int[] end, boolean[][] isVisited, int[][] grid,int pathLen,int curLen) {
        //找到一个答案
        if (begin[0] == end[0] && begin[1] == end[1]) {
            return 1;
        } else {
            int pathCount = 0;
            int[] next = new int[2];
            //往上
            next[0] = begin[0] - 1;
            next[1] = begin[1];
            if (canVisited(next, end, isVisited, grid, pathLen, curLen)) {
                isVisited[next[0]][next[1]] = true;
                pathCount += Dfs(next, end, isVisited, grid, pathLen, curLen + 1);
                isVisited[next[0]][next[1]] = false;
            }

            //往下
            next[0] = begin[0] + 1;
            next[1] = begin[1];
            if (canVisited(next, end, isVisited, grid, pathLen, curLen)) {
                isVisited[next[0]][next[1]] = true;
                pathCount +=Dfs(next, end, isVisited, grid, pathLen, curLen + 1);
                isVisited[next[0]][next[1]] = false;
            }

            //往左
            next[0] = begin[0];
            next[1] = begin[1] - 1;
            if (canVisited(next, end, isVisited, grid, pathLen, curLen)) {
                isVisited[next[0]][next[1]] = true;
                pathCount +=Dfs(next, end, isVisited, grid, pathLen, curLen + 1);
                isVisited[next[0]][next[1]] = false;
            }

            //往右
            next[0] = begin[0];
            next[1] = begin[1] + 1;
            if (canVisited(next, end, isVisited, grid, pathLen, curLen)) {
                isVisited[next[0]][next[1]] = true;
                pathCount += Dfs(next, end, isVisited, grid, pathLen, curLen + 1);
                isVisited[next[0]][next[1]] = false;
            }
            return pathCount;
        }
    }

    public static boolean canVisited(int[] next, int[] end, boolean[][] isVisited, int[][] grid, int pathLen, int curLen) {
        int nextX = next[0];
        int nextY = next[1];
        int endX = end[0];
        int endY = end[1];
        //超出边界
        if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length) {
            return false;
        }
        // 已经访问或不通
        if (isVisited[nextX][nextY] || grid[nextX][nextY] == -1) {
            return false;
        }
        // 当下一个节点是终点时，必须已经访问完其他所有节点
        if (nextX == endX && nextY == endY && pathLen - 1 != curLen) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1,0,0,0},
                {0,0,0,0},
                {0,0,2,-1}
        };
        System.out.println(uniquePathsIII(grid));
    }
}
