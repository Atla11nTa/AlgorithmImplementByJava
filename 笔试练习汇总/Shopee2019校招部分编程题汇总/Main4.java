package 笔试练习汇总.Shopee2019校招部分编程题汇总;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] matrix = new int[n][n];
        List<int[]> rooms = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = in.nextInt();
                if (matrix[i][j] == 1) {
                    rooms.add(new int[]{i, j});
                }
            }
        }
        if (rooms.size() == n * n) {
            System.out.println(-1);
            return;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    int len = 0;
                    for (int[] room : rooms) {
                        len += Math.abs(room[0] - i) + Math.abs(room[1] - j);
                    }
                    res = Math.min(res, len);
                }
            }
        }
        System.out.println(res);
    }
}
