package LeetCode;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 题目:151. 翻转字符串里的单词
 * 要求: 忽略多余的空格.
 */

public class Problem151 {
    public static String reverseWords(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    public static void main(String[] args) {
        System.out.print(reverseWords("  hello world!  "));
    }
}
