package LeetCode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 题目: 239. 滑动窗口最大值
 * 思路: 使用双端队列, 队中保存索引
 * 前k-1个先处理.
 * 如果比队尾元素小, 入队;若是大于队尾元素,那么队尾元素持续出队.
 * 若若当前索引-队首索引>=k,说明队首元素已经滑出窗口了, 队首出队, 这也是为什么队中保存索引.
 */

public class Problem239 {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> queue = new ArrayDeque<>();
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < k - 1; i++) {
            while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.offer(i);
        }
        for (int i = k - 1; i < nums.length; i++) {
            // 队尾持续出队
            while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.offer(i);
            // 说明队首元素滑出窗口了
            if (i - queue.peekFirst() >= k) {
                queue.pollFirst();
            }
            // 收集结果
            res[index++] = nums[queue.peekFirst()];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.print(Arrays.toString(maxSlidingWindow(new int[]{1, 3, 1, 2, 0, 5}, 3)));
    }
}
