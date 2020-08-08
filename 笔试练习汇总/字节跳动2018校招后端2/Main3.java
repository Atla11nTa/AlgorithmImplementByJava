package 笔试练习汇总.字节跳动2018校招后端2;

import java.util.*;

/**
 * 题目：求在m次相邻交换之后，能够得到所有字符都是同一字符的最大字符串长度。
 *
 * 思路：首先统计每种字符出现的所有位置。
 * 比如'a'出现在0,3,4
 * 对于每种字符，计算在m次交换之内能够得到的最长连续长度。
 * 用二分法来找这个最大长度，检测mid长度能否在m次交换得到，以此二分。
 * ！！n个位置最短的交换次数移到一起的方法就是两边往最中间移。
 */

public class Main3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        int m = in.nextInt();
        char[] chars = str.toCharArray();
        HashMap<Character, List<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            List<Integer> list = hashMap.get(chars[i]);
            if (list == null) {
                list = new ArrayList<>();
                list.add(i);
                hashMap.put(chars[i], list);
            } else {
                list.add(i);
            }
        }
        int res = Integer.MIN_VALUE;
        for (var list : hashMap.values()) {
            int left = 1;
            int right = 1100;
            int mid;
            while (left <= right) {
                mid = left + (right - left) / 2;
                // 如果可以将mid个位置移到一起
                if (check(list, m, mid)) {
                    res = Math.max(res, mid);
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        System.out.print(res);
    }

    // 判断在m次交换中能不能将list中minCount个位置移到一起
    static boolean check(List<Integer> list, int m, int minCount) {
        if (minCount > list.size()) {
            return false;
        }
        if (minCount == 1) {
            return true;
        }
        // 两边往中间移，最短。
        // 每次考察i-i+minCount-1这 minCount个数。
        for (int i = 0; i <= list.size() - minCount; i++) {
            int count = 0;
            int midIndex = i + (minCount - 1) / 2;
            // 把左边的依次移向中间
            int left = list.get(midIndex) - 1;
            for (int j = midIndex - 1; j >= i; j--) {
                count += left - list.get(j);
                left--;
            }
            // 把右边的依次移向中间
            int right = list.get(midIndex) + 1;
            for (int j = midIndex + 1; j <= i + minCount - 1; j++) {
                count += list.get(j) - right;
                right++;
            }
            // 移动次数小于m，表示可以完成
            if (count <= m) {
                return true;
            }
        }
        return false;
    }
}
