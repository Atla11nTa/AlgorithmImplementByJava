package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目: 567. 字符串的排列
 * 题目描述: 判断s2是否包含s1的排列
 * 解法一: 暴力法, 先求s1的排列,然后检查s2是否包含s1的某个排列. 时间复杂度O(n!). 检查一个字符串是否是另一个的子串, str1.contains(str2).
 * 解法二: 滑动窗口, s2包含s1的排列,即是s2的某个子串包含s1全部的字符. 所以使用滑动窗口, 检查s2每个长度等于s1的子串是否包含所有的字符即可.
 * 1. hashMap保存s1每个字符的频数
 * 2. matchLen保存s1中字符的种类数
 * 3. left指向窗口的开始,right指向窗口的结束.
 *
 * 滑动过程:
 * 1. 拓展: 一开始right向右拓展, left不动, 拓展过程将hashMap中对应字符的频数-1, 如果-1后 hashMap中该字符的频数为0, 说明已经收集到了一类字符, matchLen--
 * 2. 移动窗口:  第二步缩进窗口, 每次先移动left, hashMap[left]--, 检查hashMap[left] 是否为1, 如果为1, 说明该字符在之前是需要的, 现在被移除了, matchLen++
 * 然后是拓展窗口, 与步骤1相同,每次right右移一位.
 */
public class Problem567 {

    public static boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) {
            return false;
        }
        int[] hashMap = new int[256];
        //统计s1的字符种类数
        int matchLen = 0;
        for (int i = 0; i < s1.length();i++) {
            if (hashMap[s1.charAt(i)] == 0) {
                matchLen++;
            }
            hashMap[s1.charAt(i)]++;
        }
        int left = 0;
        int right = 0;
        while (right < s2.length()) {
            if (right - left < s1.length()) {
                char ch = s2.charAt(right);
                hashMap[ch]--;
                // 说明窗口内已有全部的ch类字符
                if (hashMap[ch] == 0) {
                    matchLen--;
                }
                right++;
            } else {
                char chLeft = s2.charAt(left);
                char chRight = s2.charAt(right);
                //窗口左边缩进一位
                hashMap[chLeft]++;
                //这一步需要理解, 为什么是1? 因为chLeft这个字符之前在hashMap中经历过减1操作
                // 如果该频数为负数, 说明这个字符是多余的,扔掉它也不影响matchLen
                // 如果是正数, 说明该字符本身就缺, 扔掉它同样不影响matchLen
                // 只有是0的时候, 说明之前该字符是刚好够的, 现在被扔掉, matchLen需要+1
                if (hashMap[chLeft] == 1) {
                    matchLen++;
                }
                left++;
                //窗口右边拓展一位
                hashMap[chRight]--;
                if (hashMap[chRight] == 0) {
                    matchLen--;
                }
                right++;
            }
            if (matchLen == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidbaoo";

        System.out.print(checkInclusion(s1, s2));
    }
}
