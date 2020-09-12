package LeetCode.Tree;

import java.util.TreeSet;

public class Problem {
    public static void main(String[] args) {
        int x = 0;
        int left = 0;
        int right = x;
        int mid = 0;
        int res = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (mid * mid == x) {
                res = mid;
                break;
            } else if (mid * mid > x) {
                right = mid - 1;
            } else {
                res = mid;
                left = mid + 1;
            }
        }
        System.out.println(res);
    }
}
