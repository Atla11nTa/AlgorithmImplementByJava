package LeetCode;

/**
 * 题目: 289. 生命游戏
 * 地址: https://leetcode-cn.com/problems/game-of-life/
 * 思路: 题目很简单, 但是O(1)空间复杂度的解法还是很值得学习的
 * 因为数组中的状态就0,1两种, 需要同时修改整个数组, 每次元素当前状态只与上一时刻有关, 所以不能直接原地的把状态修改, 不然会影响之后的判断.
 * 但是想原地修改也是可行的, 若从1->0, 就先把状态改为-1,表明上一时刻状态是1, 下一时刻是0, 同理, 0->1,就把状态改为2.
 * 最后遍历一遍数组,然后把-1改为0, 2改为1即可
 */

public class Problem289 {
    public void gameOfLife(int[][] board) {
        int row = board.length;
        int col = board[0].length;
        int[][] res = new int[row][col];
        int[][] index = new int[][]{
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };
        int count;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                count = 0;
                for (var next : index) {
                    if (i + next[0] < 0 || i + next[0] >= row || j + next[1] < 0 || j + next[1] >= col) {
                        continue;
                    }
                    count += board[i + next[0]][j + next[1]];
                }
                if (count < 2) {
                    res[i][j] = 0;
                } else if (count > 3) {
                    res[i][j] = 0;
                } else if (count == 3) {
                    res[i][j] = 1;
                } else {
                    res[i][j] = board[i][j];
                }
            }
        }
        for (int i = 0; i < row; i++) {
            System.arraycopy(res[i], 0, board[i], 0, col);
        }
    }
}
