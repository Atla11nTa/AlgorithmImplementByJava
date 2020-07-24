package LeetCode;

/**
 * 题目：75. 颜色分类
 * 思路：双指针
 */
public class Problem75 {
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public static void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int num;
        int index = 0;
        while (index <= right) {
            num = nums[index];
            if (num == 0) {
                swap(nums, left, index);
                left++;
                index++;
            } else if (num == 2) {
                swap(nums, index, right);
                right--;
            } else {
                index++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 0, 1};
        sortColors(nums);
    }
}
