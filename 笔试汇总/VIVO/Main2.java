package 笔试汇总.VIVO;

import java.util.Scanner;

public class Main2 {

    public static boolean isPali(char[] chars,int ignoreIndex){
        int n = chars.length;
        int end = n - 1;
        for (int i = 0; i <= n / 2; i++) {
            if (i == ignoreIndex) {
                i++;
            }
            if (end == ignoreIndex) {
                end--;
            }
            if (chars[i] != chars[end]) {
                return false;
            }
            end--;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] chars = in.next().toCharArray();
        int n = chars.length;
        for (int i = 0; i < n; i++) {
            if (isPali(chars, i)) {
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        System.out.print(chars[j]);
                    }
                }
                return;
            }
        }
        System.out.print("false");
    }
}
