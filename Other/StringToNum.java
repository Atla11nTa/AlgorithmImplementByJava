package Other;

import java.util.HashMap;

/**
 * 题目：一种字符串和数字的对应关系
 * 题目描述：一个字符数组，数组中每个字符都不同，例如char=['A','B','C']，那么"A" ->1 "B"->2 "C"->3 "AA"->4 "AB"->5
 * 解题思路： 本质就是进制转换。假设n表示字符数组的长度。
 * 数字转字符串就是 十进制转n进制。只是这个n进制是用字符表示的。
 * 字符串转数字就是 n进制数转10进制。
 */
public class StringToNum {
    public static int stringToNum(String str, char[] chs) {
        int res = 0;
        //存储每个字符在字符数组中的顺序位置。
        int[] hashMap = new int[128];
        for (int i = 0; i < chs.length; i++) {
            hashMap[chs[i]] = i + 1;
        }
        char[] strArr = str.toCharArray();
        // 字符数组长度代表进制数
        int chsSize = chs.length;
        int index = 1;
        for (int i = strArr.length - 1; i >= 0; i--) {
            res += hashMap[strArr[i]] * index;
            index *= chsSize;
        }
        return res;
    }

    public static String numToString(char[] chs, int num) {
        StringBuilder stringBuilder = new StringBuilder();
        int base = chs.length;
        int cur = 0;
        while (num != 0) {
            cur = num % base;
            num /= base;
            stringBuilder.append(chs[cur]);
        }
        return stringBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        char[] chs = new char[]{'A','B','C'};
        System.out.println(stringToNum("CCC", chs));
        System.out.println(numToString(chs,39));
    }
}
