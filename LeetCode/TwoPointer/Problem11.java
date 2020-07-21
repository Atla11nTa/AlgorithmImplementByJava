package LeetCode.TwoPointer;

/**
 * 题目：11.盛最多水的容器。
 * 思路：双指针。计算left与right之间的盛水量，因为是left与right二者较小值决定容器高度，所以每次移动较小者。
 * 若是移动较大者，肯定得不到更大的值，因为容器高度不会增加，宽度反而降低了。
 */
public class Problem11 {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = Integer.MIN_VALUE;
        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
