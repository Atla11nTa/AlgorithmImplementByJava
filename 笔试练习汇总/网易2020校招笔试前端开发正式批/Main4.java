package 笔试练习汇总.网易2020校招笔试前端开发正式批;
import java.util.Scanner;

/**
 * 贪心思维。注意题意，能修改成0,1,2,3,4。。。这样的序列就可以。
 */

public class Main4 {
    private static boolean check(long[] nums, int n, long m) {
        if (nums[0] != 0) {
            m += nums[0];
            nums[0] = 0;
        }
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1] + 1) {
                m -= nums[i - 1] + 1 - nums[i];
                if (m < 0) {
                    return false;
                }
            } else  if (nums[i] > nums[i - 1] + 1){
                m += nums[i] - nums[i - 1] - 1;
            }
            nums[i] = nums[i - 1] + 1;
        }

        return true;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseNum = in.nextInt();
        for (int i = 0; i < caseNum; i++) {
            int n = in.nextInt();
            long m = in.nextLong();
            long[] nums = new long[n];
            for (int j = 0; j < n; j++) {
                nums[j] = in.nextLong();
            }
            System.out.println(check(nums, n, m) ? "YES" : "NO");
        }
    }
}
