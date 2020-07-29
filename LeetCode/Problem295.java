package LeetCode;

import java.util.PriorityQueue;

class MedianFinder {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
    }

    private void adjustHeap() {
        int maxSize = maxHeap.size();
        int minSize = minHeap.size();
        if (maxSize - minSize > 1) {
            minHeap.offer(maxHeap.poll());
            return;
        }
        if (minSize - maxSize > 1) {
            maxHeap.offer(minHeap.poll());
        }
    }
    public void addNum(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.offer(num);
            return;
        }
        if (num < maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        adjustHeap();
    }

    public double findMedian() {
        if (maxHeap.isEmpty()) {
            return 0;
        }
        int maxSize = maxHeap.size();
        int minSize = minHeap.size();
        if (maxSize == minSize) {
            return (double) ((maxHeap.peek() + minHeap.peek()) / 2.0);
        } else if (maxSize > minSize) {
            return (double) maxHeap.peek();
        } else {
            return (double) minHeap.peek();
        }
    }
}
