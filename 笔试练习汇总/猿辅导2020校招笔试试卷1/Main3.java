package 笔试练习汇总.猿辅导2020校招笔试试卷1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main3 {
    public static String translate(String string, int count) {
        String res = string;
        for (int i = 1; i < count; i++) {
            res = res.concat(string);
        }
        return res;
    }
    public static StringBuilder transition(char[] chars, int start) {
        StringBuilder stringBuilder = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(chars[i])) {
                StringBuilder countStringBuilder = new StringBuilder();
                countStringBuilder.append(chars[i]);
                while (i + 1 < len && Character.isDigit(chars[i + 1])) {
                    countStringBuilder.append(chars[i + 1]);
                    i++;
                }
                int count = Integer.parseInt(countStringBuilder.toString());
                StringBuilder temp = new StringBuilder();
                if (stack.peek() == ')') {
                    char ch = stack.pop();
                    while ((ch = stack.pop()) != '(') {
                        temp.append(ch);
                    }
                    String res = translate(temp.reverse().toString(), count);
                    for (int j = 0; j < res.length(); j++) {
                        stack.push(res.charAt(j));
                    }
                } else {
                    char ch = stack.pop();
                    for (int j = 0; j < count; j++) {
                        stack.push(ch);
                    }
                }
            } else {
                stack.push(chars[i]);
            }
        }
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pollLast());
        }
        return stringBuilder;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseNum = in.nextInt();
        char[] chars;
        for (int i = 0; i < caseNum; i++) {
            chars = in.next().toCharArray();
            System.out.println(transition(chars, 0).toString());
        }
    }
}
