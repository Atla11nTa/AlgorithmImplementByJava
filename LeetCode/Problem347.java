package LeetCode;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 题目：347. 前 K 个高频元素
 * 思路： 堆排序
 */

public class Problem347 {
    static class Pair{
        int num;
        int count;

        Pair(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }
    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            hashMap.put(nums[i], hashMap.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<Pair> heap = new PriorityQueue<>((a,b)->{
            return a.count - b.count;
        });
        int count = 0;
        for (var kv : hashMap.entrySet()) {
            // 前k个直接入堆
            if (count < k) {
                heap.offer(new Pair(kv.getKey(), kv.getValue()));
                count++;
                continue;
            }
            Pair cur = new Pair(kv.getKey(), kv.getValue());
            if (cur.count > heap.peek().count) {
                heap.poll();
                heap.offer(cur);
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = heap.poll().num;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6, 7, 7, 8, 2, 3, 1, 1, 1, 10, 11, 5, 6, 2, 4, 7, 8, 5, 6};
        topKFrequent(nums, 10);
    }
}
