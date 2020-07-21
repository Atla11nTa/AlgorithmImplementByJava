package LeetCode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Problem {
    public static int myAtoi(String str) {
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }
        char ch = str.charAt(0);
        int beginIndex = 0;
        int endIndex = 0;
        if (ch == '-') {
            beginIndex = 1;
            endIndex = 1;
        }
        if (ch == '+') {
            str = str.substring(1);
        }
        while (endIndex < str.length()) {
            ch = str.charAt(endIndex);
            if (ch >= '0' && ch <= '9') {
                endIndex++;
            } else {
                break;
            }
        }
        String str2 = str.substring(beginIndex, endIndex);
        if (str2.length() == 0) {
            return 0;
        }
        if (beginIndex == 0 && str2.length() > 15) {
            return Integer.MAX_VALUE;
        } else if (beginIndex == 1 && str2.length() > 15) {
            return Integer.MIN_VALUE;
        }
        long res = 0;
        long mask = 1;
        char[] strArr = str2.toCharArray();
        for (int i = strArr.length - 1; i >= 0; i--) {
            res += (strArr[i] - '0') * mask;
            mask *= 10;
            if (beginIndex == 0 && res > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else if (beginIndex == 1 && -res < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
        if (beginIndex == 1) {
            res = -res;
        }
        return (int) res;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi(""));
    }
}
