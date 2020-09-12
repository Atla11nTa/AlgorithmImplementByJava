package 笔试汇总.科大讯飞;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] chars = in.next().toCharArray();
        int n = chars.length;
        int index = 0;
        int begin = 0;
        //去头部的 _
        while (chars[begin] == '_') {
            begin++;
        }
        for (int i = begin; i < n; i++) {
            if (chars[i] == '_') {
                while (i + 1 < n && chars[i + 1] == '_') {
                    i++;
                }
                //尾部单独考虑
                if (i == n - 1 && chars[i] == '_') {
                    continue;
                }
            }
            chars[index] = chars[i];
            index++;
        }
        for (int i = 0; i < index; i++) {
            System.out.print(chars[i]);
        }
    }
}
