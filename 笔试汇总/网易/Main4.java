package 笔试汇总.网易;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] nums = new int[m];
        Map<Integer, Integer[]> hashMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            // 顶点1
            int u = in.nextInt();
            // 顶点2
            int v = in.nextInt();
            // 权值
            int w = in.nextInt();
            nums[i] = w;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m - 1; i++) {
            for (int j = i + 1; j < m; j++) {
                res = Math.min(res, Math.abs(nums[i] - nums[j]));
            }
        }
        System.out.println(res);
    }
}
