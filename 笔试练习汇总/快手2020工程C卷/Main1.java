package 笔试练习汇总.快手2020工程C卷;

import java.util.Scanner;

/**
 * 题目： 找连续子串中1的数目为k的子串总数。
 * 思路：
 * 思路1： 暴力法，双重循环，超时。
 * 思路2： 滑动窗口，和平常解法不同，每次right都需要从left位置开始滑动。
 * 思路3： DP法
 */

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        String str = in.next();
        char[] chars = str.toCharArray();
        int res = 0;
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            int oneCount = 0;
            for (int j = i; j < len; j++) {
                if (chars[j] == '1') {
                    oneCount++;
                }
                if (oneCount == k) {
                    res++;
                } else if (oneCount > k) {
                    break;
                }
            }
        }
        System.out.println(res);
    }
}
