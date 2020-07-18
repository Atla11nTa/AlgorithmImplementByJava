package LeetCode;

import java.util.Arrays;

/**
 * 题目: 3.无重复字符的最长子串
 * 地址: https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * 思路: 用一个hashmap存储每个字符上一次出现的位置.
 */
public class Problem3 {
    public static int lengthOfLongestSubstring(String s) {
        int res = 0;
        int[] hashMap = new int[128];
        Arrays.fill(hashMap, -1);
        char[] strArr = s.toCharArray();
        int curLen = 0;
        for (int i = 0; i < strArr.length; i++) {
            char ch = strArr[i];
            if (hashMap[ch] == -1 || hashMap[ch] < i - curLen) {
                curLen++;
            } else {
                res = Math.max(res, curLen);
                curLen = i - hashMap[ch];
            }
            hashMap[ch] = i;
        }
        res = Math.max(res, curLen);
        return res;
    }

    public static void main(String[] args) {
        System.out.print(lengthOfLongestSubstring("pwwkew"));
    }
}
