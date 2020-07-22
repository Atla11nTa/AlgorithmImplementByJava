package LeetCode.DP;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 题目： 139.单词拆分
 * 思路：dp[i]表示从0到i这部分单词能否被表示。dp[i] = dp[j] && wordSet.contains(word(i,j))， 遍历算出一个为true即退出。
 *
 */

public class Problem139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (wordSet.contains(s.substring(0, i + 1))) {
                dp[i] = true;
                continue;
            }
            // 穷举这部分是否能被表示。
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j + 1, i + 1))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length() - 1];
    }
}
