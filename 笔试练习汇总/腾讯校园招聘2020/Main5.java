package 笔试练习汇总.腾讯校园招聘2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main5 {
    public static int getRes(int[][] nums, int L) {
        int len = nums.length;
        Arrays.sort(nums,(a,b)->{
            return a[0] - b[0];
        });
        if (nums[0][0] > 0) {
            return -1;
        }
        List<int[]> list = new ArrayList<>();
        list.add(nums[0]);
        for (int i = 1; i < len; i++) {
            if (nums[i][1] < list.get(list.size() - 1)[1]) {
                continue;
            }
            if (list.get(list.size() - 1)[1] >= L) {
                return list.size();
            }
            while (list.size() >= 2 && nums[i][0] <= list.get(list.size() - 2)[1]) {
                list.remove(list.size() - 1);
            }
            list.add(nums[i]);
        }
        return list.get(list.size() - 1)[1] >= L ? list.size() : -1;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i][0] = in.nextInt();
            nums[i][1] = in.nextInt();
        }
        System.out.println(getRes(nums, m));
    }
}
