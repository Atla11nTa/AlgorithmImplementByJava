package ArrayAndMatrix;

import java.util.PriorityQueue;

/**
 * 题目：分金条的最小开销
 * 题目描述： 金条总长度为n，需要将金条分割为数组arr记录的长度。长度为M的金条每次分割的需要的开销为M。求完全分割最小的开销。
 * 题目思路： 哈夫曼编码的思想。通过小根堆完成。
 *
 */
public class MinSplitCost {
    public static int solution(int[] arr, int n) {
        int cost = 0;
        //优先队列，默认小根堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (var num : arr) {
            minHeap.offer(num);
        }
        int sum = 0;
        while (minHeap.size() > 1) {
            sum = minHeap.poll() + minHeap.poll();
            minHeap.offer(sum);
            cost += sum;
        }
        return cost;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{10, 30, 20};
        System.out.println(solution(arr, 60));
    }
}
