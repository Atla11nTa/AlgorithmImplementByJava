package LeetCode.BinarySearch;

/**
 * 题目: LCP 12. 小张刷题计划
 * 地址: https://leetcode-cn.com/problems/xiao-zhang-shua-ti-ji-hua/
 * 思路: 二分+滑动窗口.
 * 滑动窗口: 累计窗口内的和, 每轮滑动代表一天, 因为每天可以动用一次小杨, 当和超过延定时间时,把最大值交给小杨做,然后继续滑动.
 */
public class Problem_LCP12  {

    private static boolean canFinish(int[] time, int m, int maxTime) {
        boolean flag;
        int left = 0;
        int right = 0;
        int day = 0;
        int len = time.length;

        while (right < len) {
            // 当前窗口的和
            int sum = 0;
            int max = Integer.MIN_VALUE;
            flag = false;
            // 一轮滑动过程. 代表一天
            while ((sum <= maxTime || !flag)&&right<len) {
                sum += time[right];
                max = Math.max(max, time[right]);
                // 如果当前窗口内和大于限定值
                if (sum > maxTime) {
                    // 可以动用小杨, 把最大值给小杨做
                    if (!flag) {
                        sum -= max;
                        flag = true;
                    }
                    // 无法动用小杨
                    else {
                        break;
                    }
                }
                right++;
            }
            day++;
            left = right;
        }
        return day <= m;
    }

    public static int minTime(int[] time, int m) {
        int left = 0;
        int right = Integer.MAX_VALUE;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (canFinish(time, m, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] time = new int[]{1, 2, 3, 3};
        System.out.print(minTime(time, 2));
        System.out.print(canFinish(time, 2, 3));
    }
}
