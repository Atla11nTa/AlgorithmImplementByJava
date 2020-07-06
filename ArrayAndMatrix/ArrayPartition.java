package ArrayAndMatrix;

import java.lang.module.FindException;
import java.time.chrono.MinguoDate;
import java.util.Arrays;

/**
 * 题目：数组划分
 * 题目描述： 将数组划分为三部分，左边小于k，中间等于k，右边大于k
 * left指向左部分的尾，right指向右部分的头，index进行遍历。
 */
public class ArrayPartition {
    public static void solution(int[] arr, int k) {
        int left = -1;
        int right = arr.length;
        int i = 0;
        while (i < right) {
            //left到i之间的数都是等于k的数，所以交换后考察下一个
            if (arr[i] < k) {
                swap(arr, ++left, i++);
            }
            // i和right之间的数并不确定，所以right--换到前面后，还要继续考察i。
            else if (arr[i] > k) {
                swap(arr, --right, i);
            }
            //等于k，继续。
            else {
                i++;
            }
        }
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 4, 1, 2, 1, 6, 7, 5, 4, 3};
        solution(arr, 4);
        System.out.println(Arrays.toString(arr));
    }
}
