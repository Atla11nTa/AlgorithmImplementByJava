package 笔试练习汇总.网易2020校招笔试数据分析师正式批;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述： 有a,b,c 3堆葡萄，由三个人去吃，1可以吃a,b两堆额葡萄，2可以池b,c两堆，3可以吃c,a两堆。
 * 求3个人吃完所有的葡萄，并且三人吃的最多的那个吃得最少。
 *
 * 思路：典型的最大值最小值问题，采用二分法找这个最小值。
 * check()函数用于判断能否吃完，注意在找答案的时候，应该先吃葡萄最多的那堆.
 * 所以先排序。
 */
public class Main2 {
    static long[] temp = new long[3];
    private static boolean check(long[] nums, long max) {
        // 如果最大值都小于等于max，肯定能吃完，直接返回true
        if (nums[2]<=max) {
            return true;
        }
        // 最后一个吃完还剩nums[2]-max，如果大于max，说明吃不完了。
        if (nums[2] - max > max) {
            return false;
        }
        return nums[0] + nums[1] + nums[2] <= 3 * max;
    }
    private static long getMax(long[] nums) {
        int maxIndex = 0;
        // 先排序，解答更简单
        Arrays.sort(nums);
        long min = Long.MAX_VALUE;
        long left = 0;
        long right = nums[0] + nums[1] + nums[2];
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (check(nums, mid)) {
                min = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return min;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseNum = in.nextInt();
        long[] nums = new long[3];
        for (int i = 0; i < caseNum; i++) {
            for (int j = 0; j < 3; j++) {
                nums[j] = in.nextLong();
            }
            System.out.println(getMax(nums));
        }
    }
}
