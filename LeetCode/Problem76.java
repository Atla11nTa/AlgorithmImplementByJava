package LeetCode;

/**
 * 题目: 76. 最小覆盖子串
 * 思路: 滑动窗口
 */
public class Problem76 {
    public static String minWindow(String s, String t) {
        char[] charsT = t.toCharArray();
        char[] charsS = s.toCharArray();
        int[] hash = new int[128];
        int matchLen = 0;
        for (var ch : charsT) {
            if (hash[ch] == 0) {
                matchLen++;
            }
            hash[ch]++;
        }
        int left = 0, right = 0;
        int len = charsS.length;
        char ch;
        int minLen = Integer.MAX_VALUE;
        int minIndex = -1;
        while (right < len) {
            // right扩张过程
            ch = charsS[right];
            hash[ch]--;
            // 完成一次匹配
            if (hash[ch] == 0) {
                matchLen--;
            }
            // 全部字符匹配完成
            if (matchLen == 0) {
                // 左边缩进
                ch = charsS[left];
                while (hash[ch] < 0) {
                    left++;
                    hash[ch]++;
                    ch = charsS[left];
                }
                // 记录最短
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minIndex = left;
                }
                // 左边移动一位
                hash[ch]++;
                left++;
                matchLen++;
            }
            right++;
        }
        return minIndex == -1 ? "" : s.substring(minIndex, minIndex + minLen);
    }

    public static void main(String[] args) {
        System.out.print(minWindow("ADOBECODEBANC","ABC"));
    }

}
