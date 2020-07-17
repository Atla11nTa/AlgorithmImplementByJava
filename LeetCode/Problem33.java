package LeetCode;

/**
 * 题目： 33. 搜索旋转排序数组
 * 思路：在有序部分二分查找。注意边界问题。
 */
public class Problem33 {
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left =0;
        int right = nums.length - 1;
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            //左侧有序
            if (nums[mid] > nums[left]) {
                if (nums[left] == target) {
                    return left;
                }
                //数在左边
                if (nums[left] < target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[right] == target) {
                    return right;
                }
                //数在右边
                if (nums[mid] < target && target < nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,1};
        System.out.println(search(nums, 1));
    }
}
