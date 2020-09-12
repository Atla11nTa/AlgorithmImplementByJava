package LeetCode;

public class Problem498 {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int len = row * col;

        int[] res = new int[len];
        int index = 0;
        boolean bottomToTop = true;
        int x = 0, y = 0;
        while (index < len) {
            if (bottomToTop) {
                while (index < len && x >= 0 && x < row && y >= 0 && y < col) {
                    res[index++] = matrix[x][y];
                    x--;
                    y++;
                }
                x++;
                if (y >= col) {
                    x++;
                    y--;
                }
            } else {
                while (index < len && x >= 0 && x < row && y >= 0 && y < col) {
                    res[index++] = matrix[x][y];
                    x++;
                    y--;
                }
                y++;
                if (x <= 0) {
                    y++;
                    x--;
                }
            }
            bottomToTop = !bottomToTop;
        }
        return res;
    }
}
