package 笔试汇总.Shopee;

import javax.print.DocFlavor;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] str = in.next().split(",");
        String[] str1 = str[0].split("\\.");
        String[] str2 = str[1].split("\\.");
        int len1 = str1.length;
        int len2 = str2.length;
        int index = 0;
        int num1;
        int num2;
        while (index < len1 && index < len2) {
            num1 = Integer.parseInt(str1[index]);
            num2 = Integer.parseInt(str2[index]);
            if (num1 < num2) {
                System.out.print(-1);
                return;
            }
            if (num1 > num2) {
                System.out.print(1);
                return;
            }
            index++;
        }
        if (index == len1 && index == len2) {
            System.out.print(0);
            return;
        }
        if (index == len1) {
            while (index < len2) {
                num2 = Integer.parseInt(str2[index]);
                if (num2 > 0) {
                    System.out.print(-1);
                    return;
                }
                index++;
            }
            System.out.print(0);
        } else {
            while (index < len1) {
                num1 = Integer.parseInt(str1[index]);
                if (num1 > 0) {
                    System.out.print(1);
                    return;
                }
                index++;
            }
            System.out.print(0);
        }
    }
}
