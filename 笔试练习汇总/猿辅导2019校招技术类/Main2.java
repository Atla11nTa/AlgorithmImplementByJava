package 笔试练习汇总.猿辅导2019校招技术类;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useDelimiter("\\n");
        int n = in.nextInt();
        String str = in.next();
        char[] chars = new char[n];
        int charIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                chars[charIndex++] = str.charAt(i);
            }
        }
        int i = 0;
        int x = (n - 1) / 3;
        int row = 2 * x + 1;
        int leftCount = 0;
        int midCount = 2 * x - 1;
        int index = 0;
        for (i = 0; i < row && index < n; i++) {
            int j = leftCount;
            while (j > 0) {
                System.out.print(" ");
                j--;
            }
            if (i < x) {
                System.out.print(chars[index++]);
                j = midCount;
                while (j > 0) {
                    System.out.print(" ");
                    j--;
                }
                System.out.print(chars[index++]);
            } else {
                System.out.print(chars[index++]);
            }
            midCount = Math.max(0, midCount - 2);
            leftCount = Math.min(x, leftCount + 1);
            System.out.println();
        }
    }
}
