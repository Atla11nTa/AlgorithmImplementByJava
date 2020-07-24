package LeetCode;

public class Problem {
    private boolean isExist = false;

    private boolean canVisit(char[][] board, char[] word, boolean[][] isVisited, int start, int x, int y) {
        // 越界
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }
        // 已访问
        if (isVisited[x][y]) {
            return false;
        }
        // 检查是否匹配
        return board[x][y] == word[start];
    }

    private void backTrack(char[][] board, char[] word, boolean[][] isVisited, int start, int x, int y) {
        if (isExist) {
            return;
        }
        // 匹配完成
        if (start == word.length) {
            isExist = true;
        } else {
            int nextX;
            int nextY;
            // 向上匹配
            nextX = x - 1;
            nextY = y;
            if (canVisit(board, word, isVisited, start, nextX, nextY)) {
                isVisited[nextX][nextY] = true;
                backTrack(board, word, isVisited, start, nextX, nextY);
                isVisited[nextX][nextY] = false;
            }
            //向下匹配
            nextX = x + 1;
            if (canVisit(board, word, isVisited, start, nextX, nextY)) {
                isVisited[nextX][nextY] = true;
                backTrack(board, word, isVisited, start, nextX, nextY);
                isVisited[nextX][nextY] = false;
            }
            //向左匹配
            nextX = x;
            nextY = y - 1;
            if (canVisit(board, word, isVisited, start, nextX, nextY)) {
                isVisited[nextX][nextY] = true;
                backTrack(board, word, isVisited, start, nextX, nextY);
                isVisited[nextX][nextY] = false;
            }
            // 向右匹配
            nextY = y + 1;
            if (canVisit(board, word, isVisited, start, nextX, nextY)) {
                isVisited[nextX][nextY] = true;
                backTrack(board, word, isVisited, start, nextX, nextY);
                isVisited[nextX][nextY] = false;
            }
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
