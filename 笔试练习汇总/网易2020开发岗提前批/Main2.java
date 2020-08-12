package 笔试练习汇总.网易2020开发岗提前批;

import java.util.Scanner;

public class Main2 {
    private static boolean check(int[][] gird, int lenX, int lenY) {
        int row = gird.length;
        int col = gird[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (gird[i][j] == 1) {
                    continue;
                }
                // 横放
                int k=i, m=j;
                outer1:
                for (k=i; k < i + lenX && k < row; k++) {
                    for (m=j; m < j + lenY && m < col; m++) {
                        if (gird[k][m] == 1) {
                            break outer1;
                        }
                    }
                }
                if (k == i + lenX && m == j + lenY&& gird[k-1][m-1] == 0) {
                    return true;
                }

                // 竖放
                outer2:
                for (k = i; k < i + lenY && k < row; k++) {
                    for (m = j; m < j + lenX && m < col; m++) {
                        if (gird[k][m] == 1) {
                            break outer2;
                        }
                    }
                }
                if (k == i + lenY && m == j + lenX&& gird[k-1][m-1] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseNum = in.nextInt();
        for (int i = 0; i < caseNum; i++) {
            int row = in.nextInt();
            int col = in.nextInt();
            int blockCount = in.nextInt();
            int[][] gird = new int[row][col];
            // 把障碍物位置置为1
            for (int j = 0; j < blockCount; j++) {
                int x = in.nextInt();
                int y = in.nextInt();
                gird[x - 1][y - 1] = 1;
            }
            int lenX = in.nextInt();
            int lenY = in.nextInt();
            if (check(gird, lenX, lenY)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
