package 笔试练习汇总.网易2020校招笔试Java开发正式批;

import java.util.*;

/**
 * 逆序对问题。
 * 归并排序。
 */

public class Main3 {
    private static int[] tempArr;
    private static int[] originIndex;
    private static int[] tempIndex;
    private static long res = 0;

    private static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, mid + 1, right);
    }

    private static void merge(int[] nums, int left1, int right1, int left2, int right2) {
        int index = left1;
        int begin1 = left1;
        int begin2 = left2;
        while (begin1 <= right1 && begin2 <= right2) {
            if (nums[begin1] <= nums[begin2]) {
                tempIndex[index] = originIndex[begin1];
                //nums[begin1] 比 nums[left2...begin2-1]的数都大，统计一下结果
                for (int i = left2; i < begin2; i++) {
                    res += Math.max(originIndex[i] - originIndex[begin1], 0);
                }
                tempArr[index++] = nums[begin1++];
            } else {
                tempIndex[index] = originIndex[begin2];
                tempArr[index++] = nums[begin2++];
            }
        }
        while (begin1 <= right1) {
            tempIndex[index] = originIndex[begin1];
            // begin2左边的都比begin小，统计一下结果。
            for (int i = left2; i < begin2; i++) {
                res += Math.max(originIndex[i] - originIndex[begin1], 0);
            }
            tempArr[index++] = nums[begin1++];
        }
        while (begin2 <= right2) {
            tempIndex[index] = originIndex[begin2];
            tempArr[index++] = nums[begin2++];
        }
        for (int i = left1; i <= right2; i++) {
            nums[i] = tempArr[i];
            originIndex[i] = tempIndex[i];
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        tempArr = new int[n];
        originIndex = new int[n];
        tempIndex = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
            originIndex[i] = i;
        }
        mergeSort(nums, 0, n - 1);
        System.out.println(res);
    }
}
