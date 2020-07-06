package ArrayAndMatrix;

/**
 * 题目: 未排序数组中累加和小于或等于给定值的最长子数组长度
 * 思路分析： 对于数组Arr, 求出一个数组sumArr, sumArr[i]表示数组Arr中i及左侧所有元素和
 * 根据sumArr再求解一个helpArr，helpArr[i]表示sumArr中i及左侧元素的最大值，所以helpArr是一个有序非递减的数组，可利用二分进行查找。
 * 假设目标是k， 遍历数组时，设sum[i]-k = x，那么sum[i]减去前面大于等于x的部分，就满足题目的累加和小于等于给定值的要求，同时要求最长子数组，
 * 那么就找大于等于x这个最左出现的位置。
 * 为了加速查找过程，创建一个helper数组，保存左侧累加和的最大值，helper数组是有序，就可以执行二分查找快速找到满足要求的x。
 *
 */
public class MaxLenSubArraySumK {

    //二分查找数组中大于等于num的数最早出现的位置
    public static int binarySearch(int[] arr, int num) {
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (num >= arr[mid]) {
                //每次都记录，最后就是保存的最左。
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
    public static int solution(int[] arr, int k) {
        int maxLen = Integer.MIN_VALUE;
        int[] sumArr = new int[arr.length];
        int[] helpArr = new int[arr.length];
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            sumArr[i] = sum;
            maxSum = Math.max(maxSum, sumArr[i]);
            helpArr[i] = maxSum;
        }
        int res = 0;
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            res = sumArr[i] - k;
            index = binarySearch(helpArr, res);
            int len = index == -1 ? 0 : i - index;
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, -2, -4, 0, 6};
        System.out.println(solution(arr,-2));
    }
}
