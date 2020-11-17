package 笔试汇总.京东;

import java.util.Scanner;

public class Main1 {

    public static void check(String str) {
        try {
            int year = Integer.parseInt(str);
            if (year >= 1000 && year <= 3999) {
                System.out.print(year+" ");
            }
        } catch (NumberFormatException e) {
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.nextLine();
            char[] chars = str.toCharArray();
            int len = chars.length;
            int begin = 0;
            char ch;
            for (int i = 0; i < len; i++) {
                ch = chars[i];
                if (!Character.isDigit(ch) && !Character.isLetter(ch)) {
                    if (i > begin) {
                        check(String.valueOf(chars, begin, i - begin));
                    }
                    begin = i + 1;
                }
            }
        }
    }
}
