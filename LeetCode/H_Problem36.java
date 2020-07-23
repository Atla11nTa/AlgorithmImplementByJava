package LeetCode;

/**
 * 36. 有效的数独
 * 思路:
 * 用三个哈希表分别记录每行每列以及每个3x3矩阵中数字出现的次数.
 * 这里如何把行列号映射到第几个box使用了一个技巧,可以学习一下.
 */

public class H_Problem36 {
    public boolean isValidSudoku(char[][] board) {
        // 记录每一行元素出现的次数
        int[][] rowMap = new int[9][10];
        // 记录每一列元素出现的次数
        int[][] colMap = new int[9][10];
        // 记录每个3*3元素出现的次数, 把9个3x3的小数组映射到0-9个数,使用(row/3)*3+col/3.
        int[][] boxMap = new int[9][10];
        int num;
        int boxIndex;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    num = board[i][j] - '0';
                    boxIndex = (i / 3) * 3 + j / 3;
                    if (rowMap[i][num] != 0 || colMap[j][num] != 0 || boxMap[boxIndex][num] != 0) {
                        return false;
                    }
                    rowMap[i][num]++;
                    colMap[j][num]++;
                    boxMap[boxIndex][num]++;
                }
            }
        }
        return true;
    }
}
