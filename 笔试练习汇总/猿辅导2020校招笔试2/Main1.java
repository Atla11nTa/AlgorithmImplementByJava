package 笔试练习汇总.猿辅导2020校招笔试2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Map<Integer, Integer> hashMap = new HashMap<>(n);
        int m = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            nums[i] = num;
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            if (hashMap.get(nums[i]) <= m) {
                System.out.print(nums[i] + " ");
            }
        }
    }
}
