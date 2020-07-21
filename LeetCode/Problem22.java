package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目: 22. 括号生成
 * 思路: 回溯法.
 *
 */

public class Problem22 {
    public void backTrack(List<String> res, List<Character> cur, int left, int right, int n) {
        if (left + right == 2 * n) {
            StringBuilder str = new StringBuilder();
            for (var ch : cur) {
                str.append(ch);
            }
            res.add(str.toString());
        } else {
            if (left < n) {
                cur.add('(');
                backTrack(res, cur, left + 1, right, n);
                cur.remove(cur.size() - 1);
            }
            if (right < left) {
                cur.add(')');
                backTrack(res, cur, left, right + 1, n);
                cur.remove(cur.size() - 1);
            }
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        List<Character> cur = new ArrayList<>();
        backTrack(res, cur, 0, 0, n);
        return res;
    }
}
