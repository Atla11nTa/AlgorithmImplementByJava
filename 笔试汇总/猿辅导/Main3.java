package 笔试汇总.猿辅导;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int left = 1;
        int right = n;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (mid == 1 || mid == n) {
                break;
            }
        }
    }
}
