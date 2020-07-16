package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 题目：914.卡牌分组
 * 链接：https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards/
 * 题目描述： 把arr分为n组，每组元素相同，且n个组元素个数相同且超过2个。
 * 解题思路： 先统计每个元素出现的次数, 因为题目给出了数的最大范围，所以使用数组hash。
 * 求所有元素次数的最大公约数，若最大公约数大于等于2,说明可以划分。
 */
public class Problem914 {
    public static boolean hasGroupsSizeX(int[] deck) {
        //记录每个元素出现的次数
        int[] count = new int[10000];
        for (int num : deck) {
            count[num]++;
        }
        int g = -1;
        for (int i = 0; i < 10000; ++i)
            if (count[i] > 0) {
                if (g == -1)
                    g = count[i];
                else
                    g = gcd(g, count[i]);
            }

        return g >= 2;
    }

    public static int gcd(int x, int y) {
        return x == 0 ? y : gcd(y % x, x);
    }

    public static void main(String[] args) {
        System.out.println(hasGroupsSizeX(new int[]{1, 1, 1, 1, 2, 2, 2, 2, 2, 2}));
    }
}
