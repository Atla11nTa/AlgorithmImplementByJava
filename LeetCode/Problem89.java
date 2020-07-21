package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：89. 格雷编码
 * 思路：观察规律，G(n+1)可由G(n)推导得到, 把G(n)的答案逆序高位添加1得到G(n+1)
 * G(0) G(1) G(2) G(3)
 * 0    0     00  000
 *      1     01  001
 *            11  111
 *            10  110
 *                101
 *                100
 */
public class Problem89 {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        int head = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = res.size() - 1; j >= 0; j--) {
                res.add(head + res.get(j));
            }
            head = head << 1;
        }
        return res;
    }
}
