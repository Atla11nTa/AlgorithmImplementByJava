package 笔试练习汇总.猿辅导2020试卷3;

import java.util.Scanner;

public class Main2 {
    private static int circle(int[][] m, int[] res,int index, int leftUpX, int leftUpY, int rightDownX, int rightDownY) {
        int i = leftUpX;
        int j = leftUpY;
        // 仅一行
        if (leftUpX == rightDownX) {
            while (j <= rightDownY) {
                res[index++] = m[leftUpX][j++];
            }
            return index;
        }
        // 仅一列
        if (leftUpY == rightDownY) {
            while (i <= rightDownX) {
                res[index++] = m[i++][j];
            }
            return index;
        }
        // 从上往下
        while (i < rightDownX) {
            res[index++] = m[i++][j];
        }
        // 从左往右
        while (j < rightDownY) {
            res[index++] = m[i][j++];
        }
        //从下往上
        while (i > leftUpX) {
            res[index++] = m[i--][j];
        }
        while (j > leftUpY) {
            res[index++] = m[i][j--];
        }
        return index;
    }
    public static void circlePrint(int[][] m) {
        int row = m.length;
        if (row == 0) {
            return;
        }
        int col = m[0].length;
        int leftUpX = 0;
        int leftUpY = 0;
        int rightDownX = row - 1;
        int rightDownY = col - 1;
        int[] res = new int[row * col];
        int index = 0;
        while (leftUpX <= rightDownX && leftUpY <= rightDownY) {
            index = circle(m, res, index, leftUpX, leftUpY, rightDownX, rightDownY);
            leftUpX++;
            leftUpY++;
            rightDownX--;
            rightDownY--;
        }
        for (int i = 0; i < index - 1; i++) {
            System.out.print(res[i] + "\t");
        }
        System.out.print(res[index-1]);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        circlePrint(matrix);
    }
}
