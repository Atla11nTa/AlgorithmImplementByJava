package LeetCode;

import java.util.*;

/**
 * 题目: 207. 课程表
 * 地址: https://leetcode-cn.com/problems/course-schedule/
 * 思路: 利用BFS进行拓扑排序, 每次选择入度为0的节点入队
 * 依据入度数组和后继节点进行BFS.
 */
public class H_Problem_207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 记录每个节点入度
        int[] indegrees = new int[numCourses];
        // 记录每个节点的后继节点
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
        // 记录每个节点的入度. cp[0]->cp[1]
        for (int[] cp : prerequisites) {
            // cp[1]的入度++
            indegrees[cp[1]]++;
            // cp[0]的后继加上cp[1]
            adjacency.get(cp[0]).add(cp[1]);
        }
        // 执行广度优先遍历
        Queue<Integer> queue = new LinkedList<>();
        // 每次把入度为0的节点入队
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) queue.add(i);
        }
        // BFS拓扑排序
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            // 删除一个节点
            numCourses--;
            // 遍历后继节点, 后继节点的入度--, 若入度为0, 加入queue
            for (int next : adjacency.get(cur)) {
                if (--indegrees[next] == 0) queue.add(next);
            }
        }
        return numCourses == 0;
    }
}
