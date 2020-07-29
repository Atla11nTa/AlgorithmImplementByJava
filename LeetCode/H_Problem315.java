package LeetCode;

import java.util.Arrays;
import java.util.List;

/**
 * 题目: 315. 计算右侧小于当前元素的个数
 * 类似的: 右侧大于(归并降序), 左侧小于, 左侧大于
 * 地址: https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/
 * 思路一: 朴素思路双重循环统计即可.
 * 思路二: 利用归并排序统计.复杂度O(nlogn)
 * 因为归并排序是相邻之间比较并且排序, 所以在归并之前两个序列元素之间的相对顺序与初始是相同的, 在归并的过程中统计即可.
 * 难点在于如何记录答案, 因为归并过程中元素的位置一直在改变,方法就是用一个index数组记录元素的位置, 归并过程也跟着改变这个数组.
 * 假设一个数组: 5,2,6,1   index: 0,1,2,3
 * 第一次归并是: 5和2, 归并后是: 2,5 index: 1,0,2,3, 同时0号位置元素的结果+1
 * 归并的时候, 索引跟着元素一起变, 元素的索引始终与开始时相同.
 */
public class H_Problem315 {
    // 朴素的方法,双重循环
    public List<Integer> countSmaller(int[] nums) {
        Integer[] res = new Integer[nums.length];
        int len = nums.length;
        int count;
        for (int i = len - 1; i >= 0; i--) {
            count = 0;
            for (int j = i + 1; j < len; j++) {
                if (nums[i] > nums[j]) {
                    count++;
                }
            }
            res[i] = count;
        }
        return Arrays.asList(res);
    }


    // 记录答案
    Integer[] res;
    // 归并过程中的临时数组
    int[] mergeTemp;
    // 记录元素的索引,初始为0,1,2,3...n-1
    int[] index;
    // 归并过程中的临时数组
    int[] indexTemp;

    // 主要函数,在归并过程中计算右侧比自身小的数
    private void merge(int[] nums, int l1, int r1, int l2, int r2) {
        int i = l1, j = l2;
        int begin = l1;
        while (i <= r1 && j <= r2) {
            // 左侧一个数比右侧的小, 这时要顺便统计右侧比自己小的数的个数
            if (nums[i] <= nums[j]) {
                mergeTemp[begin] = nums[i];
                // i这个元素之后在begin位置, index[begin]位置存的就是index[i], 变动的时候索引也跟着变
                indexTemp[begin] = index[i];
                // 右侧比自己小的个数是j-l1
                res[index[i]] += (j - l1);
                ++i;
            } else {
                mergeTemp[begin] = nums[j];
                indexTemp[begin] = index[j];
                ++j;
            }
            begin++;
        }
        while (i <= r1) {
            mergeTemp[begin] = nums[i];
            indexTemp[begin] = index[i];
            res[index[i]] += (j - l1);
            ++i;
            ++begin;
        }
        while (j <= r2) {
            mergeTemp[begin] = nums[j];
            indexTemp[begin] = index[j];
            ++j;
            ++begin;
        }
        for (int k = l1; k <= r2; k++) {
            index[k] = indexTemp[k];
            nums[k] = mergeTemp[k];
        }
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, mid + 1, right);
    }


    public List<Integer> countSmaller2(int[] nums) {
        int len = nums.length;
        this.res = new Integer[len];
        this.mergeTemp = new int[len];
        this.index = new int[len];
        this.indexTemp = new int[len];
        for (int i = 0; i < len; i++) {
            index[i] = i;
            res[i] = 0;
        }
        mergeSort(nums, 0, len - 1);
        return Arrays.asList(this.res);
    }

    public static void main(String[] args) {
        H_Problem315 p = new H_Problem315();
        p.countSmaller2(new int[]{1, 1, 2, 1, 4});
    }
}
