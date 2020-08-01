package LeetCode;
import java.util.*;

/**
 * 题目：632. 最小区间
 * 思路：转化问题： 这个问题就是在每个区间都取一个数，然后组成一个最小的区间。
 * 采用最小堆的办法， 从左到右，先把第一列的数全部加入堆中。
 * 每次出堆时，出堆元素就是当前区间的最小值，然后计算当前区间长度是否小于最小区间长度，小于就更新结果。
 * 然后把出堆元素所在列的下一个入堆，因为此元素入堆才有可能缩小区间，想象一下，将其他入队，区间肯定是增大的。
 * 由此不断的入堆出堆，最终但一个元素走到终点就结束。
 * 思路巧妙，好好揣摩
 */

public class H_Problem632{
    public static int[] smallestRange(List<List<Integer>> nums) {
        // 保存最终最小区间的结果
        int rangeLeft = Integer.MAX_VALUE;
        int rangeRight = Integer.MIN_VALUE;
        int rangeLen = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            return a[2] - b[2];
        });
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            int num = nums.get(i).get(0);
            // 堆中节点，第一个数是该num所在行，第二个数是所在列， 第三个数是num的值
            minHeap.add(new int[]{i, 0, num});
            max = Math.max(max, num);
        }
        int[] cur;

        while (true) {
            cur = minHeap.poll();
            // 当前区间更小, cur[2]就是当前的左边界, max就是右边界
            if (max - cur[2] < rangeLen) {
                rangeLen = max - cur[2];
                rangeLeft = cur[2];
                rangeRight = max;
            }
            // 某一行元素全部访问完
            if (cur[1] + 1 == nums.get(cur[0]).size()) {
                break;
            }
            minHeap.offer(new int[]{cur[0], cur[1] + 1, nums.get(cur[0]).get(cur[1] + 1)});
            max = Math.max(max, nums.get(cur[0]).get(cur[1] + 1));
        }
        return new int[]{rangeLeft, rangeRight};
    }

    public static void main(String[] args) {
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(new ArrayList<>(Arrays.asList(1,2,3)));
        nums.add(new ArrayList<>(Arrays.asList(1,2,3)));
        nums.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        System.out.print(Arrays.toString(smallestRange(nums)));
    }
}
