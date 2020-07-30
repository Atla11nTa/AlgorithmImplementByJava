package LeetCode.DP;

/**
 * 题目: 10.正则表达式匹配
 * 思路: 这道题用回溯能过, 用动态规划太复杂了.
 */
public class Problem10 {
    private boolean match(char[] str, int stri, char[] exp, int expi) {
        // 匹配成功
        if (expi == exp.length) {
            return stri == str.length;
        }
        // 如果下一个字符不是'*'
        if (expi + 1 == exp.length || exp[expi + 1] != '*') {
            return stri != str.length && (exp[expi] == str[stri] || exp[expi] == '.') && match(str, stri + 1, exp, expi + 1);
        }
        // 如果下一个字符是'*', 匹配0位或者多位
        while (stri != str.length && (exp[expi] == str[stri] || exp[expi] == '.')) {
            // 完成匹配
            if (match(str, stri, exp, expi + 2)) {
                return true;
            }
            stri++;
        }
        return match(str, stri, exp, expi + 2);
    }
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return true;
        }
        char[] str = s.toCharArray();
        char[] exp = p.toCharArray();
        return match(str, 0, exp, 0);
    }
}
