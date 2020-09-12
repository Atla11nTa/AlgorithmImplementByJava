package 笔试汇总.科大讯飞;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main4 {
    static List<Integer> pureList;

    // 判断是否为质数
    public static boolean isPureNum(int n) {
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 得到比n小的质数列表
    public static List<Integer> getPureArr(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i <= n/2; i++) {
            if (isPureNum(i)) {
                list.add(i);
            }
        }
        return list;
    }


    // 递归
    public static String getNum(int n) {
        if (isPureNum(n)) {
            return String.valueOf(n);
        }
        int pure = -1;
        for (int i = 0; i < pureList.size(); i++) {
            if (n % pureList.get(i) == 0) {
                pure = pureList.get(i);
                break;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(pure).append("*");
        return stringBuilder.append(getNum(n/pure)).toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        pureList = getPureArr(n);
        System.out.print(getNum(n));
    }
}
