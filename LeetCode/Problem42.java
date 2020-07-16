package LeetCode;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

/**
 * 题目: 42. 接雨水
 * 思路: 计算每一格能装的水. 格子上能装的水 = max { min{leftMax[i] , rightMax[i]} - height[i] , 0}
 */
public class Problem42 {
    public static int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int len = height.length;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];

        int max;
        //求左边的最大值
        leftMax[0] = -1;
        max = height[0];
        for (int i = 1; i < leftMax.length; i++) {
            leftMax[i] = max;
            max = Math.max(max, height[i]);
        }

        max = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = max;
            max = Math.max(max, height[i]);
        }
        int res = 0;

        for (int i = 1; i < len - 1; i++) {
            int min = Math.min(leftMax[i], rightMax[i]);
            res += min > height[i] ? min - height[i] : 0;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] height = new int[]{2, 0, 2};
        System.out.print(trap(height));
    }
}
