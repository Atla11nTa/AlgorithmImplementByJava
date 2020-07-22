package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：131. 分割回文串
 * 思路：回溯。还可以利用DP加速验证【i,j】是否为回文串。
 */
class Solution {
    private boolean isPalindrome(String s, int begin, int end) {
        while (begin < end) {
            if (s.charAt(begin) == s.charAt(end)) {
                begin++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }
    private void backTrack(List<List<String>> res, List<String> cur, String s, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<>(cur));
        } else {
            // 截取start到i部分。
            for (int i = start; i < s.length(); i++) {
                if (isPalindrome(s, start, i)) {
                    cur.add(s.substring(start, i + 1));
                    backTrack(res, cur, s, i + 1);
                    cur.remove(cur.size() - 1);
                }
            }
        }

    }
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        backTrack(res, new ArrayList<>(), s, 0);
        return res;
    }
}
