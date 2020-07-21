package LeetCode;

/**
 * 题目: 2的幂
 * 思路:
 * 因为2的幂的二进制表示中只有一个1,所以判断n是否只有一个1即可.
 * 1. 取出二进制最右边的一个1, x&-x
 * 2. 去掉二进制最右边的一个1,x&(x-1)
 * 这两个操作在位运算中十分有用
 */

public class Problem231 {
    public static boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return false;
        }
        long x = n;
//        return (n & (-n)) == n;
        return (x & (x - 1)) == 0;
    }

    public static void main(String[] args) {
        System.out.print(isPowerOfTwo(Integer.MIN_VALUE));

    }
}
