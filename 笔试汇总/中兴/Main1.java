package 笔试汇总.中兴;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int sum;
        int[] nums = new int[n];
        int avg;
        int[] res = new int[n];
        for (int i = 0; i < m; i++) {
            sum = 0;
            for (int j = 0; j < n; j++) {
                nums[j] = in.nextInt();
                sum += nums[j];
            }
            avg = sum / n;
            for (int j = 0; j < n; j++) {
                if (nums[j] > avg) {
                    res[j] = 1;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (res[i] == 1) {
                count++;
            }
        }
        System.out.println(count);
    }
}
