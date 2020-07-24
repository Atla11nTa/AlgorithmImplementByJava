package LeetCode.BinarySearch;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 思路：二分查找
 */
public class Problem34 {
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = -1;
        boolean flag = true;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                flag = false;
                break;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // 没找到
        if (flag) {
            return new int[]{-1, -1};
        }
        int begin = mid;
        int end = mid;
        while (begin - 1 >= 0 && nums[begin - 1] == target) {
            begin--;
        }
        while (end + 1 < nums.length && nums[end+1] == target) {
            end++;
        }
        return new int[]{begin, end};
    }

}
