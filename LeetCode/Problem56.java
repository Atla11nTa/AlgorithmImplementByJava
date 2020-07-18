package LeetCode;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 题目：56.合并区间
 * 地址： https://leetcode-cn.com/problems/merge-intervals/
 *
 * 思路：为了方便合并，先排序，根据左边界升序排序.
 * 然后进行合并过程, 以cur为基准，让intervals[i]与cur比较，若能合并就合并，若不能合并，
 * 因为已经排序过，所以之后的左边界都比intervals[i]的大，之后的就更不能合并，所以一个结果就找到了，cur添加到结果集，接着intervals[i]作为cur
 * 继续合并过程。
 */
public class Problem56 {
    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });
        List<int[]> res = new ArrayList<>();
        int[] cur = new int[2];
        cur[0] = intervals[0][0];
        cur[1] = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            //能合并就合并
            if (intervals[i][0] <= cur[1]) {
                cur[1] = Math.max(intervals[i][1], cur[1]);
            }
            // 不能合并说明一个结果找到
            else {
                res.add(new int[]{cur[0], cur[1]});
                cur[0] = intervals[i][0];
                cur[1] = intervals[i][1];
            }
        }
        res.add(cur);
        return res.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] inter = new int[][]{
                {1, 3}, {2, 6}, {8, 10}, {15, 18}
        };
        System.out.println(Arrays.deepToString(merge(inter)));

    }
}
