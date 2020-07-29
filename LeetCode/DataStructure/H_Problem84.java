package LeetCode.DataStructure;

import java.util.*;

/**
 * 题目： 84. 柱状图中最大的矩形
 * 地址： https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 * 思路： 为了计算最大矩阵， 就考察每个位置能分别向左和向右拓展多远， 最远就是最后一个比自己大或者等于的位置， 或者说第一个比自己小的前一个位置。
 * 单调栈刚好可以用于计算离自己最近的最大或者最小值。
 * 为了计算右边最近的较小值， 从左往右遍历， 维持一个单增栈， 当元素i比栈顶元素小时，说明栈顶元素右边最近的数就是i，为了记录，栈中存放索引。
 * 同样的为了计算左边的较小值， 从右往左遍历， 维持一个单增栈。
 *
 * 总结： 单调栈用于计算数组中离自己最近的较大或较小元素。
 * 特点： 栈中存放索引， 单增栈计算较小值， 单减栈计算较大值， 往右遍历计算右边最小最大， 往左遍历计算左边最小最大。
 */

public class H_Problem84 {
    public static int largestRectangleArea(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        int len = heights.length;
        int[] rightMin = new int[len];
        int[] leftMin = new int[len];
        Arrays.fill(rightMin, -1);
        Arrays.fill(leftMin, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        // 求右最近比i小的数的索引
        for (int i = 0; i < len; i++) {
            // i比栈顶小，就持续出栈
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int index = stack.pop();
                rightMin[index] = i;
            }
            stack.push(i);
        }
        stack = new ArrayDeque<>();
        for (int i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int index = stack.pop();
                leftMin[index] = i;
            }
            stack.push(i);
        }
        int max = Integer.MIN_VALUE;
        int left;
        int right;
        for (int i = 0; i < len; i++) {
            left = leftMin[i] == -1 ? 0 : leftMin[i] + 1;
            right = rightMin[i] == -1 ? len - 1 : rightMin[i] - 1;
            max = Math.max(max, heights[i] * (right - left + 1));
        }
        return max;
    }

    public static void main(String[] args) {
        largestRectangleArea(new int[]{0});
    }
}
