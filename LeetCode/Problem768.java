package LeetCode;

/**
 * 题目: 768.最多能完成排序的块II
 * 链接:https://leetcode-cn.com/problems/max-chunks-to-make-sorted-ii/
 * 思路: 题目要求是划分后分别排序,最终连接在一起也是有序的,所以只需要先求出每个元素左边的最大值和右边的最小值,只要leftMax<=rightMin,就说明
 * 在i位置可以划分
 */
public class Problem768 {
    public static int maxChunksToSorted(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 1;
        }
        int res = 0;
        int len = arr.length;
        int[] leftMax = new int[len];
        int[] rightMin = new int[len];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            max = Math.max(max, arr[i]);
            leftMax[i] = max;
        }
        int min = Integer.MAX_VALUE;
        rightMin[len - 1] = min;
        for (int i = len - 2; i >= 0; i--) {
            rightMin[i] = Math.min(min, arr[i + 1]);
            min = rightMin[i];
        }
        for (int i = 0; i < len; i++) {
            if (leftMax[i] <= rightMin[i]) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.print(maxChunksToSorted(new int[]{4, 2, 2, 1, 1}));
    }
}
