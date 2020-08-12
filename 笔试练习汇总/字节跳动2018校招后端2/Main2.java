package 笔试练习汇总.字节跳动2018校招后端2;

import java.util.Scanner;

/**
 * 题目：简单题，注意细心
 */

public class Main2 {
    public static void main(String[] args) {
        int n, m, c;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        c = in.nextInt();
        // c+1行，n列，记录c+1中颜色珠子出现的位置。
        int[][] matrix = new int[c + 1][n];
        for (int i = 0; i < n; i++) {
            int colorCount = in.nextInt();
            if (colorCount == 0) {
                matrix[0][i] = 1;
            }
            for (int j = 0; j < colorCount; j++) {
                int color = in.nextInt();
                matrix[color][i] = 1;
            }
        }
        int res = 0;
        for (int i = 1; i < c + 1; i++) {
            // 记录上一次出现的位置
            int pre = -1;
            // 记录出现的第一个位置
            int first = -1;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    if (pre == -1) {
                        pre = j;
                        first = j;
                    } else {
                        if (j - pre < m) {
                            res++;
                            break;
                        }
                        pre = j;
                    }
                }
            }
            // 开头结尾一致
            if (pre != first && n - pre + first < m) {
                res++;
            }
        }
        System.out.print(res);
    }
}
