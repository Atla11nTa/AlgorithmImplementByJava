package LeetCode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 题目：215. 数组中的第K个最大元素
 * 思路1：用小顶堆找最大的K个数，最后堆顶即是第K个元素。
 * 思路2：如果不让用优先队列，那么就用快排的划分思想。
 */
public class Problem215 {
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        System.out.println(findKthLargest(nums, 2));
    }
}
