package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目:17. 电话号码的字母组合
 * 思路: 回溯法
 */
public class Problem17 {
    char[] map = new char[]{
            0, 0, 'a', 'd', 'g', 'j', 'm', 'p', 't', 'w'
    };
    private void backTrack(List<String> res, StringBuilder cur, char[] digits, int start) {
        if (start == digits.length) {
            res.add(cur.toString());
        } else {
            int curNum = digits[start] - '0';
            char beginChar = map[curNum];
            int len = 3;
            if (curNum == 7 || curNum == 9) {
                len = 4;
            }
            for (int i = 0; i < len; i++) {
                cur.append(beginChar);
                backTrack(res, cur, digits, start + 1);
                cur.delete(cur.length() - 1, cur.length());
                beginChar++;
            }
        }
    }
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        backTrack(res, new StringBuilder(), digits.toCharArray(), 0);
        return res;
    }
}
