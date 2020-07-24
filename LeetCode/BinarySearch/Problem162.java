package LeetCode.BinarySearch;

/**
 * 题目： 162. 寻找峰值
 * 思路二分查找
 */
public class Problem162 {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            // mid处于下峰阶段， 峰顶在左边（自己也可能是峰顶）
            if (nums[mid] > nums[mid + 1])
                r = mid;
            // mid处于上峰阶段。
            else
                l = mid + 1;
        }
        return l;
    }
}
