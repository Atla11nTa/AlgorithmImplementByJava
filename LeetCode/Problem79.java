package LeetCode;

/**
 * 题目：79. 单词搜索
 */

public class Problem79 {
    private boolean isExist = false;

    private void canVisit(char[][] board, char[] word, boolean[][] isVisited, int start, int x, int y) {
        // 越界
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return;
        }
        // 已访问
        if (isVisited[x][y]) {
            return;
        }
        // 检查是否匹配
        if (board[x][y] == word[start]) {
            isVisited[x][y] = true;
            backTrack(board, word, isVisited, start+1, x, y);
            isVisited[x][y] = false;
        }
    }

    private void backTrack(char[][] board, char[] word, boolean[][] isVisited, int start, int x, int y) {
        if (isExist) {
            return;
        }
        // 匹配完成
        if (start == word.length) {
            isExist = true;
        } else {
            canVisit(board, word, isVisited, start, x - 1, y);
            canVisit(board, word, isVisited, start, x + 1, y);
            canVisit(board, word, isVisited, start, x, y - 1);
            canVisit(board, word, isVisited, start, x, y + 1);
        }
    }

    public boolean exist(char[][] board, String word) {
        int Row = board.length;
        int Col = board[0].length;
        char[] wordArr = word.toCharArray();
        boolean[][] isVisited;
        out:
        for (int i = 0; i < Row; i++) {
            for (int j = 0; j < Col; j++) {
                if (board[i][j] == wordArr[0]) {
                    isVisited = new boolean[Row][Col];
                    isVisited[i][j] = true;
                    backTrack(board, wordArr, isVisited, 1, i, j);
                    if (isExist) {
                        break out;
                    }
                }
            }
        }
        return isExist;
    }
}
