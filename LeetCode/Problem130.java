package LeetCode;

/**
 * 题目: 130. 被围绕的区域
 * 思路: 从边界为'O'的位置,开始执行图的遍历算法,将所有为'O'的变为'#', 表明这些位置是不能变动的.
 * 最后再遍历一次board, 将'#'改进去即可.
 */
public class Problem130 {
    int[][] nexts = new int[][]{
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == 'X' || board[i][j] == '#') {
            return;
        }
        board[i][j] = '#';
        for (var next : nexts) {
            dfs(board, i + next[0], j + next[1]);
        }
    }

    public void solve(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        boolean[][] isVisited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if ((i==0||i==row-1||j==0||j==col-1) && board[i][j] == 'O') {
                    dfs(board, i, j);
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }
}

