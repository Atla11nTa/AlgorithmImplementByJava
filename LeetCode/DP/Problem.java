package LeetCode.DP;

/**
 * 题目: 395.至少有K个重复字符的最长子串
 * 思路:
 * 1. 先统计字符频数, 如果最低频数大于等于k,说明整个字符串都符合要求,返回其长度.
 * 2. 如果最低频数小于k, 那么以该字符作为划分点,划分整个字符串, 因为包含该字符的子串肯定不可能是符合要求的.
 * 3. 分别在其子串中找符合要求的最长子串. 最大值是结果.
 */
public class Problem {
    public int longestSubstring(String s, int k) {
        // 先统计词频
        int[] hashmap = new int[128];
        for (int i = 0; i < s.length(); i++) {
            hashmap[s.charAt(i)]++;
        }
        // 找出现次数最小的元素
        char ch = ' ';
        int minCount = Integer.MAX_VALUE;
        for (int i = 0; i < hashmap.length; i++) {
            if (hashmap[i] != 0 && hashmap[i] < minCount) {
                minCount = hashmap[i];
                ch = (char)i;
            }
        }
        int res = 0;
        // 如果出现次数最少的元素出现次数也大于等于k,说明整个s都是答案
        if (minCount >= k) {
            return s.length();
        } else {
            int tag = 0;
            for (int i = 0; i < s.length(); i++) {
                // 以ch为划分点进行划分
                if (s.charAt(i) == ch) {
                    // 仅对长度大于k的子串进行检查, 低于k的肯定不是
                    if (i - tag + 1 >= k) {
                        res = Math.max(longestSubstring(s.substring(tag, i), k), res);
                    }
                    tag = i + 1;
                }
                // 别忘了最后一部分.
                else if (i == s.length() - 1 && i - tag + 1 >= k) {
                    res = Math.max(longestSubstring(s.substring(tag, i + 1), k), res);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Problem p = new Problem();
        System.out.print(p.longestSubstring("aacbbbdc", 2));
    }
}
