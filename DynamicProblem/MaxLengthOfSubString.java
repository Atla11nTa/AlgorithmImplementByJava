package DynamicProblem;

import java.util.Arrays;

/**
 * 题目： 最长不含重复字符串的子字符串
 * 解题思路： 用一个hashmap存储字符上一次出现的位置。
 * 假如str = 'arabcacfr'。
 * 当遍历至'ara'时，发现‘a’上次出现在0号位置，所以就记录当前子串的长度，并且curLen = i-hashMap['a']表示子串'ra'的长度。
 * 注意： 比如当前子串是'acfr'，考察到'r'时，发现'r'上次出现在1号位置，但是上一次'r'的位置已经超过了当前子串的范围，curLen应该+1操作
 */
public class MaxLengthOfSubString {
    public static int solution(String string) {
        char[] strArr = string.toCharArray();
        int maxLen = 0;
        int curLen = 0;
        int[] hashMap = new int[128];
        Arrays.fill(hashMap, -1);
        for (int i = 0; i < strArr.length; i++) {
            char ch = strArr[i];
            //当前考察的子串中没有该字符。重点是后面i-hashmap[ch]>curLen这个条件，这个条件限制了仅考察当前字串
            if (hashMap[ch] < 0 || i - hashMap[ch] > curLen) {
                curLen++;
            }
            //若已经出现过，那么就记录当前子串的长度。
            else {
                maxLen = Math.max(maxLen, curLen);
                curLen = i - hashMap[ch];
            }
            hashMap[ch] = i;
        }
        maxLen = Math.max(maxLen, curLen);
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(solution("arabcacfr"));

    }
}
