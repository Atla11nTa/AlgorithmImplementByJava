package Other;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * 题目：随机找到数据流的中位数
 * 题目描述： 设计一个MedianHolder的结构，可以随机取出之前所有数的中位数。
 * 要求：往MedianHolder添加新元素的时间复杂度是O(logn)，查找当前MedianHolder中所有元素的中位数的时间复杂度是O(1)
 * 思路分析： 数据结构用两个堆
 * 大顶堆： 保存较小部分的数，那么堆顶就是较小部分的最大值
 * 小顶堆: 保存较大部分的数，那么堆顶就是较大部分最小值
 * 用这两个堆把MedianHolder分割为均等的两部分，那么中位数就在堆顶出现。
 * 当一个新数添加时，与大顶堆堆顶比较，若较小，说明其属于较小部分，所以入大顶堆，反之入小顶堆。每次添加新数后要检查两个堆的大小，保持两个的大小均等。
 *
 */
public class FindMedian {
    static class MedianHolder {
        //为求中位数，要保持两个堆大小差距不能大于1。
        //一个小根堆，存放较大部分，堆顶是较大部分的最小值。
        private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        //一个大根堆，存放较小部分，堆顶是较小部分的最大值。
        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return t1 - integer;
            }
        });

        //检查两个堆的大小是否符合要求，必要时调整
        private void checkAndAdjust(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
            int check = maxHeap.size() - minHeap.size();
            //maxHeap元素过多
            if (check > 1) {
                minHeap.offer(Objects.requireNonNull(maxHeap.poll()));
            } else if (check < -1) {
                maxHeap.offer(Objects.requireNonNull(minHeap.poll()));
            }
        }

        //添加元素
        public void add(int num) {
            if (maxHeap.isEmpty()) {
                maxHeap.offer(num);
                return;
            }
            //maxHeap存较小值，堆顶是较小值中最大的，若num比堆顶小，说明属于较小的部分
            if (num <= maxHeap.peek()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }
            checkAndAdjust(maxHeap,minHeap);
        }

        //获取中位数
        public double getMedian() {
            if (maxHeap.isEmpty()) {
                return -1;
            }
            if (maxHeap.size() == minHeap.size()) {
                return (double) (maxHeap.peek() + minHeap.peek()) / 2.0;
            }
            return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
        }
    }

    public static void main(String[] args) {
        MedianHolder medianHolder = new MedianHolder();
        int[] arr = new int[]{5,2};
        for (var num : arr) {
            medianHolder.add(num);
        }
        System.out.println(medianHolder.getMedian());
    }
}
