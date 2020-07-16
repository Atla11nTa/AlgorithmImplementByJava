package LeetCode;

import java.util.Arrays;

/**
 * 题目: 345.反转字符串中的元音字母
 */
public class Problem345 {
    public static String reverseVowels(String s) {
        char[] strArr = s.toCharArray();
        int[] chIndex = new int[s.length()];
        Arrays.fill(chIndex, -1);
        int count = 0;
        for (int i = 0; i < strArr.length; i++) {
            char ch = strArr[i];
            if (ch >= 'a' && ch <= 'z') {
                ch = (char) (ch - 32);
            }
            if (ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
                chIndex[count++] = i;
            }
        }
        for (int i = 0; i < count / 2; i++) {
            char temp = strArr[chIndex[i]];
            strArr[chIndex[i]] = strArr[chIndex[count - i - 1]];
            strArr[chIndex[count - i - 1]] = temp;
        }
        return String.valueOf(strArr);
    }

    public static void main(String[] args) {
        System.out.print(reverseVowels("Aa"));
    }
}
