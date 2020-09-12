package LeetCode;

import java.util.*;

/**
 * 题目：332. 重新安排行程
 * 思路： 欧拉路径问题，主要问题在于dfs，顺序进行dfs，可能会进入死路，导致没有遍历完所有节点。所以换一种思路，如果走进了死路，那么这个死路肯定就是终点，
 * 所以遍历完一个节点的所有后继之后再将其加入结果，以倒序的形式加入，最后将结果reverse.
 */
public class Problem332_H {
    List<String> resList = new LinkedList<>();
    // 倒序dfs
    public void dfs(String cur, Map<String, PriorityQueue<String>> nextMap) {
        PriorityQueue<String> nexts = nextMap.get(cur);
        String temp;
        if (nexts != null) {
            while (!nexts.isEmpty()) {
                temp = nexts.poll();
                dfs(temp,nextMap);
            }
        }
        resList.add(cur);
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> nextMap = new HashMap<>();
        String from, to;
        for (var ticket : tickets) {
            from = ticket.get(0);
            to = ticket.get(1);
            PriorityQueue<String> next = nextMap.computeIfAbsent(from, k -> new PriorityQueue<>());
            next.add(to);
        }
        dfs("JFK", nextMap);
        Collections.reverse(resList);
        return resList;
    }
}
