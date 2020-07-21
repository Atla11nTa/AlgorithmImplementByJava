package LeetCode;

/**
 * 题目： 88. 合并两个有序数组
 * 思路：把较小的移到后面去
 */
public class Problem88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1;
        int index2 = n - 1;
        int right = m + n - 1;
        while (index1 >= 0 && index2 >= 0) {
            // 把较大的移到后面
            if (nums1[index1] > nums2[index2]) {
                nums1[right] = nums1[index1--];
            } else {
                nums1[right] = nums2[index2--];
            }
            right--;
        }
        while (index2 >= 0) {
            nums1[right--] = nums2[index2--];
        }
    }
}
