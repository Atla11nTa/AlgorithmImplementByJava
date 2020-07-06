package ArrayAndMatrix;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 题目：做项目的最大收益问题
 * 思路：
 * 因为项目的数量有限，所以每一次做的项目一定是可以做的里面收益最高的。
 * 小根堆根据开销保存，大根堆根据收益保存。
 * 先把所有的项目保存在小根堆中，然后把所有都可以做的项目依次取出并加到大根堆中，然后从大根堆中取出堆顶，也就是当前可做的最佳项目。
 * 因为这个问题是做项目，所以资金是越来越多的，不存在之前能做，之后不能做的情况。
 */
public class MaxProfits {
    static class Problem{
        int cost;
        int profit;
        public Problem(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    /**
     *
     * @param W : 初始资金
     * @param K ： 可完成的最大项目数
     * @param costs： 项目开销数组
     * @param profits： 项目收益数组
     * @return： 做完项目后的最大资金数值
     */
    public static int solution(int W, int K, int[] costs, int[] profits) {
        //小根堆根据开销
        PriorityQueue<Problem> costHeap = new PriorityQueue<>(new Comparator<Problem>() {
            @Override
            public int compare(Problem problem, Problem t1) {
                return problem.cost - t1.cost;
            }
        });
        //大根堆根据收益保存
        PriorityQueue<Problem> profitHeap = new PriorityQueue<>(new Comparator<Problem>() {
            @Override
            public int compare(Problem problem, Problem t1) {
                return t1.profit - problem.profit;
            }
        });
        for (int i = 0; i < costs.length; i++) {
            costHeap.offer(new Problem(costs[i], profits[i]));
        }
        for (int i = 0; i < K; i++) {
            while (!costHeap.isEmpty() && costHeap.peek().cost <= W) {
                profitHeap.offer(costHeap.poll());
            }
            if (profitHeap.isEmpty()) {
                return W;
            }
            W += profitHeap.poll().profit;
        }
        return W;
    }

    public static void main(String[] args) {

    }
}
