package LeetCode.BinarySearch;

/**
 * 题目: 1482. 制作 m 束花所需的最少天数
 * 类型: 最大值最小值问题.
 * 思路: 二分尝试在mid天能否完成任务, 若可以完成, 就right = mid-1,同时记录答案, 若无法完成就left = mid+1
 */
public class Problem1482 {
    // 检查当前的天数能否找到m束花,每束k朵
    private static boolean check(int[] bloomDay, int m, int k, int curDay) {
        int sum = 0;
        int count = 0;
        for (var bloomday : bloomDay) {
            if (curDay >= bloomday) {
                count++;
                // 找到了k束花
                if (count == k) {
                    sum++;
                    count = 0;
                }
            } else {
                count = 0;
            }
        }
        return sum >= m;
    }

    public static int minDays(int[] bloomDay, int m, int k) {
        int left = 1;
        int right = Integer.MAX_VALUE/2;
        int res = -1;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            // 如果可以完成, 就降低
            if (check(bloomDay, m, k, mid)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 10, 3, 10, 2};
        System.out.print(minDays(nums, 3, 2));
    }

}
