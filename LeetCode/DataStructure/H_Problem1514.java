package LeetCode.DataStructure;

import java.util.*;
import java.util.zip.DeflaterOutputStream;

/**
 * 1514. 概率最大的路径
 * 地址:https://leetcode-cn.com/problems/path-with-maximum-probability/
 * 思路: 带权最短路径问题.
 * 关于最短路径问题的总结:
 * 1. BFS: 适用于无权图.找最短路径. 利用toVisit/nextLevel计算路径长度,  利用map,key:cur,value:前驱 记录路径. 时间复杂度O(n^2)
 * 2. dijkstra算法: 适用于带权图, 但是存在负环时失效, 时间复杂度O(ElogV) E为边数,V是顶点数.
 * 3. Ford算法: 适用于带权图, 负环不会失效,时间复杂度O(E*V).
 */

public class H_Problem1514 {
    static class Pair{
        int node;
        double prob;
        Pair(int node,double prob) {
            this.node = node;
            this.prob = prob;
        }
    }
    /**
     * Dijkstra算法
     * 思想: 建立一个大根堆(找最大值), 遍历当前节点cur的所有邻接点next,若从cur到next更近,那么就更新next的概率值,并且入队.
     * 优点: 时间复杂度较低
     * 缺点: 会因为存在负环而失效
     */
    public static double maxProbability2(int n, int[][] edges, double[] succProb, int start, int end) {
        // 先构建邻接表, map[index]存储index的所有邻接节点,以及权重
        List<List<Pair>> map = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int node1 = edges[i][0];
            int node2 = edges[i][1];
            map.get(node1).add(new Pair(node2, succProb[i]));
            map.get(node2).add(new Pair(node1, succProb[i]));
        }

        // 建一个大根堆, 根据概率值排序
        PriorityQueue<Pair> heap = new PriorityQueue<>((a,b)->{
            return Double.compare(b.prob, a.prob);
        });

        // 存储由起点到节点i的最大概率值
        double[] prob = new double[n];
        prob[start] = 1.0;
        heap.offer(new Pair(start, 1.0));
        Pair cur;
        while (!heap.isEmpty()) {
            cur = heap.poll();
            int curNode = cur.node;
            double curPro = cur.prob;
            if (curNode == end) {
                break;
            }
            // 遍历当前节点的邻接点
            for (var next : map.get(curNode)) {
                int nextNode = next.node;
                // cur到next的概率值
                double nextProb = next.prob;
                // 如果从cur到next的概率值更大
                if (curPro * nextProb > prob[nextNode]) {
                    // 更新prob数组, 并且把下一节点入队
                    prob[nextNode] = curPro * nextProb;
                    heap.offer(new Pair(nextNode, prob[nextNode]));
                }
            }
        }
        return prob[end];
    }
    /**
     * Ford算法.
     * 思想: 遍历所有顶点, 若从顶点的相邻节点到达自己的距离更近, 那么就更新距离值为从相邻节点到达.
     * 优点: 实现简单, 且不会因为存在负环就失效
     * 缺点: 时间复杂度比Dijkstra算法高
     */
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // 存储从起点到i的概率值
        double prob[] = new double[n];
        prob[start] = 1;//起点
        while (true) {
            boolean k = false;
            // 遍历边, edges[j][0]表示顶点1, edges[j][1]表示顶点2
            for (int j = 0; j < edges.length; j++) {
                // 若经顶点1到顶点2更近, 那么就更新dp[顶点2]为由顶点1到顶点2的概率值
                if (prob[edges[j][0]] * succProb[j] > prob[edges[j][1]]) {
                    prob[edges[j][1]] = prob[edges[j][0]] * succProb[j];
                    k = true;
                }//因为是无向图,所以再反向遍历
                if (prob[edges[j][1]] * succProb[j] > prob[edges[j][0]]) {
                    prob[edges[j][0]] = prob[edges[j][1]] * succProb[j];
                    k = true;
                }
            }
            // 遍历一遍未修改表示已收敛
            if (!k) break;
        }
        return prob[end];
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {0, 1}, {1, 2}, {0, 2}
        };
        double[] prob = new double[]{0.5, 0.5, 0.2};
        maxProbability2(3, edges, prob, 0, 2);
    }
}
