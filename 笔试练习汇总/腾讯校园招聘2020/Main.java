package 笔试练习汇总.腾讯校园招聘2020;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    private static String translate(String string) {
        StringBuilder res = new StringBuilder();
        int len = string.length();
        int i = 0;
        for (i = 0; i < len; i++) {
            if (string.charAt(i) == '|') {
                break;
            }
        }
        int count = Integer.parseInt(string.substring(0, i));
        String subString = string.substring(i + 1, string.length());
        for (i = 0; i < count; i++) {
            res.append(subString);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] chars = in.next().toCharArray();
        int len = chars.length;
        Deque<Character> stack = new ArrayDeque<>();
        char ch;
        // 记录上一次左括号的位置
        int indexPer = -1;
        for (int i = 0; i < len; i++) {
            ch = chars[i];
            if (ch == '[') {
                indexPer = i;
            }
            // 完成一次翻译
            if (ch == ']') {
                StringBuilder stringBuilder = new StringBuilder();
                char ch2;
                while ((ch2 = stack.pop()) != '[') {
                    stringBuilder.append(ch2);
                }
                stringBuilder.reverse();
                String str = translate(stringBuilder.toString());
                for (int index = 0; index < str.length(); index++) {
                    stack.push(str.charAt(index));
                }
            } else {
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pollLast());
        }
    }
}
