package Backtrack;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 题目:邮局选址问题
 * 题目描述: arr记录了一系列居民点,用一维坐标表示, num表示要在num个居民点上建立邮局, 求一个邮局选址方案,使所有居民点到邮局的距离最短,返回最小距离
 * 解题思路: 暴力回溯
 * 遍历所有的居民点建立num个邮局,然后记录最小的.
 */
public class MinDistance {
    public static int solution(int[] arr, int num) {
        ArrayList<Integer> postLocations = new ArrayList<>();
        return backTrack(arr, num, postLocations, 0);
    }

    public static int backTrack(int[] arr, int maxPostCount, ArrayList<Integer> postLocations, int start) {
        //所有邮局已经选址完毕
        if (postLocations.size() == maxPostCount) {
            int res = 0;
            //遍历每个居民点,记录其到最近邮局点的距离
            for (int i = 0; i < arr.length; i++) {
                int min = Integer.MAX_VALUE;
                for (var postLocation : postLocations) {
                    min = Math.min(Math.abs(arr[i] - arr[postLocation]), min);
                }
                res += min;
            }
            return res;
        } else {
            //把最小值返回
            int min = Integer.MAX_VALUE;
            //选一个邮局位置
            for (int i = start; i < arr.length; i++) {
                postLocations.add(i);
                //进入下一次选址,从下一个点开始选择
                min = Math.min(backTrack(arr, maxPostCount, postLocations, i + 1), min);
                postLocations.remove(postLocations.size() - 1);
            }
            return min;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 1000};
        System.out.println(solution(arr, 2));
    }
}
