package LeetCode;

import java.util.*;

/**
 * 题目:49. 字母异位词分组
 * 思路: 因为每一组逆序词的所有单词排序后都一样, 所有将单词排序后的结果作为key, 逆序词序列为value, 构建一个哈希表. 比较巧妙
 */
public class Problem49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> ans = new HashMap<>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList<>());
            }
            ans.get(key).add(s);
        }
        return new ArrayList<>(ans.values());
    }
}
