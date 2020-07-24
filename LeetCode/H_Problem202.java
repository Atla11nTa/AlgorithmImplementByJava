package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * 题目： 202.快乐数
 * 思路：
 * 关于是快乐数好判断，但是若不是快乐数，如何判断呢？
 * 若不是快乐数，那么一定是进入循环了，所以用一个set保存所有出现过的数字，若一个出现过的数字再次出现，说明进入了循环，这个数肯定不是快乐数。
 * 这种技巧要学会。
 */
public class H_Problem202 {
    private int getNext(int n) {
        int next = 0;
        int temp;
        while (n > 0) {
            temp = n % 10;
            next += temp * temp;
            n /= 10;
        }
        return next;
    }
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int next;
        while (true) {
            next = getNext(n);
            if (next == 1) {
                return true;
            }
            if (set.contains(next)) {
                return false;
            }
            set.add(next);
            n = next;
        }
    }
}
