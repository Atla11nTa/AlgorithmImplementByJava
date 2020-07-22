package LeetCode;

import java.util.*;

/**
 * 题目： 140. 单词拆分 II
 * 思路：
 * 思路1： 回溯法。暴力回溯会在某些特定的case处超时，需要改进。
 * 思路2： 优化回溯。暴力回溯中有很多重复的计算，举例说明
 * 句子： “aabab” 单词集: "a","ab","b"
 * 那么表示到aab的b时，a+ab, a+a+b这两种方法都会达到同一个索引，并从此计算后续，但这两种情况只需要计算一次后续，所以可以用map把后续保存，以索引为key。
 */
public class H_Problem140 {
    HashMap<Integer, List<String>> map = new HashMap<>();
    private List<String> backTrack( String s, Set<String> wordDict, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("");
        } else {
            //从start到i这部分子串若能被表示放入结果
            for (int i = start; i < s.length(); i++) {
                String subStr = s.substring(start, i + 1);
                if (wordDict.contains(subStr)) {
                    // 后面的结果
                    List<String> list = backTrack(s, wordDict, i + 1);
                    // 收集后面的结果。
                    for (String l : list) {
                        res.add(s.substring(start, i + 1) + (l.equals("") ? "" : " ") + l);
                    }
                }
            }
        }
        map.put(start, res);
        return res;
    }
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        return backTrack( s, wordSet, 0);
    }
}


