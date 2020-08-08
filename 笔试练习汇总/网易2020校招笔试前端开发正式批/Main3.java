package 笔试练习汇总.网易2020校招笔试前端开发正式批;

import java.util.Scanner;

/**
 * 贪心策略，每次跳到可以到达的范围里，楼最高的点，如果整个范围里都跳不到，那就动用一次特殊能力跳到最高点。
 * 跳到最高点是为了之后的选择更多。
 */

public class Main3 {
    // 策略，每次跳到能跳到部分的最高处。
    private static boolean check(int[] nums, int n, int k) {

        int index = 0;
        boolean flag = true;
        while (index < n - 1) {
            int next = index + 1;
            int beginHeight = nums[index];
            int bestChooseIndex = -1;
            int maxHeightIndex = -1;
            int maxHeight = Integer.MIN_VALUE;
            while (next < n && next <= index + k) {
                if (nums[next] <= beginHeight && (bestChooseIndex == -1 || nums[next] >= nums[bestChooseIndex])) {
                    bestChooseIndex = next;
                }
                if (nums[next] >= maxHeight) {
                    maxHeightIndex = next;
                    maxHeight = nums[next];
                }
                next++;
            }
            // 说明整个范围内没有符合的
            if (bestChooseIndex == -1) {
                // 可以使用超能力
                if (flag) {
                    index = maxHeightIndex;
                    flag = false;
                    continue;
                } else {
                    return false;
                }
            }
            index = bestChooseIndex;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseNum = in.nextInt();
        for (int i = 0; i < caseNum; i++) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] nums = new int[n];
            for (int j = 0; j < n; j++) {
                nums[j] = in.nextInt();
            }
            System.out.println(check(nums, n, k) ? "YES" : "NO");
        }
    }
}
