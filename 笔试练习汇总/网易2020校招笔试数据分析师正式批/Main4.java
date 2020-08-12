package 笔试练习汇总.网易2020校招笔试数据分析师正式批;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 简单题
 */
public class Main4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] nums = new int[n];
        Map<Integer, Integer> hashMap = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
            hashMap.put(nums[i], hashMap.getOrDefault(nums[i], 0) + 1);
        }

        for (int j = 0; j < m; j++) {
            int k = in.nextInt();
            Integer res = hashMap.get(k);
            System.out.println(res == null ? 0 : res);
        }
    }
}
