package LeetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 题目： 621.任务调度器
 * 思路：
 * 1. 调度的基本思路： 因为每个任务完成后，n个时间内不能完成同种任务，为了减少系统的等待时间，我们应该优先完成任务量多的任务，然后在等待时间内完成其他的任务，
 * 之后便可以在较少或者不需要等待时间就可以接着完成任务。
 * 如何实现呢？
 * 首先对任务量计数， 然后全部进入优先队列（大顶堆），因为每一轮最多完成n+1个任务，所以每次循环n+1次，若堆不为空，就从堆中取出一个任务完成，
 * 若任务量还大于0，暂时保存，本轮结束后再入队，下轮接着完成。若堆为空，说明需要进入等待时间了。
 */
public class H_Problem621 {
    public int leastInterval(char[] tasks, int n) {
        // 先计数
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        // 建一个大顶堆，优先完成任务量多的任务。
        PriorityQueue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());
        // 然后将计算结果加入优先队列。队列里仅保存每种任务的个数。
        for (int count: map) {
            if (count > 0)
                queue.add(count);
        }
        int time = 0;
        out:
        while (!queue.isEmpty()) {
            int i = 0;
            List<Integer> temp = new ArrayList<>();
            // 每次选择最多n+1个任务。因为每个任务有n的等待时间，所以每轮最多选择n+1个任务，为了尽快完成，所以优先完成任务量多的，使其早点进入等待时间。
            while (i <= n) {
                if (!queue.isEmpty()) {
                    // 每轮里面每个任务最多完成一次，所以先取出来，若是大于1，就保存在temp里，下轮完成。
                    if (queue.peek() > 1)
                        temp.add(queue.poll() - 1);
                    else
                        queue.poll();
                    time++;
                    i++;
                } else {
                    // 全部任务已经完成，最后一轮不需要等待时间了。
                    if (temp.size() == 0)
                        break out;
                    // 本轮需要等待 n-i+1个时间
                    time += n - i + 1;
                    break;
                }
            }
            // 将本轮没完成的任务重新入队，下轮接着完成。
            for (int l: temp)
                queue.offer(l);
        }
        return time;
    }
}
