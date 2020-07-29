package LeetCode;

import java.util.Arrays;

/**
 * 题目：179. 最大数
 * 思路： 转成字符串排序即可
 */

public class Problem {
    public String largestNumber(int[] nums) {
        int len = nums.length;
        String[] strings = new String[len];
        int index = 0;
        for (var num : nums) {
            strings[index++] = String.valueOf(num);
        }
        Arrays.sort(strings,(a,b)->{
            return Long.valueOf(b + a).compareTo(Long.valueOf(a + b));
        });
        if (strings[0].equals("0")) {
            return "0";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (var str : strings) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }
}
