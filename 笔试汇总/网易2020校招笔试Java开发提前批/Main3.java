package 笔试汇总.网易2020校招笔试Java开发提前批;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] scores = new int[n];
        for (int i = 0; i < n; i++) {
            scores[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] checks = new int[m];
        double[] res = new double[m];
        for (int i = 0; i < m; i++) {
            checks[i] = in.nextInt() - 1;
        }
        for (int i = 0; i < m; i++) {
            int checkScore = scores[checks[i]];
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (scores[j] <= checkScore) {
                    count++;
                }
            }
            res[i] = (double) (count - 1) / n * 100;
        }
        for (double r : res) {
            System.out.printf("%.6f\n",r);
        }
    }
}
