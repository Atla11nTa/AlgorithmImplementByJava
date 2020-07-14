package ArrayAndMatrix;

/**
 * 题目：未排序正数数组中累加和为给定值的最长子数组长度
 * 思路分析：
 * left和right分别表示子数组的头和尾，sum代表子数组和，因为是正数数组，所以sum<k，说明此时right要右移，sum == k说明刚好符合，此时left右移，
 * 找下一个，因为right右移的子数组是肯定大于k的。sum>k时，left右移。
 * 注意每次移动left和right时都要相应的加减sum.
 */
public class SubArraySumK {
    public static int solution(int[] arr, int k) {
        // 记录最长长度
        int maxLen = 0;
        //记录子数组头
        int left = 0;
        //记录子数组尾
        int right = 0;
        //记录子数组的和
        int sum = arr[left];
        while (right < arr.length) {
            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
                sum -= arr[left];
                left++;
            } else if (sum < k) {
                right++;
                if (right == arr.length) {
                    break;
                }
                sum += arr[right];
            } else {
                sum -= arr[left];
                left++;
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,2,3,1,2,1,1,1,2},4));
    }
}
