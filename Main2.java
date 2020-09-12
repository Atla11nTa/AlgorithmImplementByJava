import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
    static double res = 0;

//    double[][][] memo = new double[][][];
    public static void dfs(double[][] Ci, double C, boolean[] isVisited, int curW, int n) {
        res = Math.max(curW, res);
        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                if (C >= Ci[i][0]) {
                    isVisited[i] = true;
                    dfs(Ci, C - Ci[i][0], isVisited, curW + (int) Ci[i][1], n);
                    isVisited[i] = false;
                } else {
                    break;
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        double C = in.nextInt();
        double[][] Ci = new double[n][2];

        for (int i = 0; i < n; i++) {
            Ci[i][0] = in.nextDouble();
            Ci[i][1] = in.nextDouble();
        }
        Arrays.sort(Ci,(a,b)->{
            return Double.compare(a[0], b[0]);
        });
        dfs(Ci, C, new boolean[n], 0, n);
        System.out.println((int) res);
    }
}
