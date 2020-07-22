package LeetCode;

public class Problem189 {
    private void reverseArr(int[] arr, int left, int right) {
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    public void rotate(int[] nums, int k) {
        int len = nums.length;
        if (len == 0) {
            return;
        }
        k = k % len;
        reverseArr(nums, 0, len - k - 1);
        reverseArr(nums, len - k, len - 1);
        reverseArr(nums, 0, len - 1);
    }
}
