package LeetCode;

public class Problem214 {
    public String shortestPalindrome(String s) {
        if (s.length()==0) {
            return s;
        }
        char[] chars = s.toCharArray();
        int len = chars.length;
        boolean[][] isPalindrome = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = i; j >= 0; j--) {
                if (j == i) {
                    isPalindrome[j][i] = true;
                } else if (j == i - 1 && chars[i] == chars[j]) {
                    isPalindrome[j][i] = true;
                } else {
                    isPalindrome[j][i] = chars[i] == chars[j] && isPalindrome[j + 1][i - 1];
                }
            }
        }
        int maxLen = 1;
        for (int j = 0; j < len; j++) {
            if (isPalindrome[0][j]) {
                maxLen = Math.max(maxLen, j + 1);
            }
        }
        StringBuilder res = new StringBuilder();
        res.append(s.substring(maxLen)).reverse();
        res.append(s);
        return res.toString();
    }
}
