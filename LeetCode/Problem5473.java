package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 5473. 灯泡开关 IV
 * 地址:https://leetcode-cn.com/problems/bulb-switcher-iv/
 * 思路: 找规律,统计块的个数,最后一个块若是0, 就减1.
 */

public class Problem5473 {
    public static int minFlips(String target) {
        int len = target.length();
        char[] chars = target.toCharArray();
        int count = 1;
        char cur;
        char pre = chars[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            cur = chars[i];
            if (cur != pre) {
                count++;
            }
            pre = cur;
        }
        if (pre == '0') {
            count--;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.print(minFlips("001011101"));

    }
}
