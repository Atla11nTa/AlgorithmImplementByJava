package LeetCode.BinarySearch;

/**
 * 题目： 69.x的平方根
 * 思路：二分查找
 * 注意：溢出。两个点, left+right可能溢出，mid*mid可能溢出。
 */
public class Problem69 {
    public static int mySqrt(int x) {
        int left=0;
        int right = x;
        int mid;
        int res = -1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            //这个left+right可能会溢出，这样写就得转为long
//            mid = (int) ((long)(left + right) / 2);
            if ((long) mid * mid <= x) {
                res = mid;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(10));
    }
}
