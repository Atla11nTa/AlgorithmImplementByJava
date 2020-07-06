package TopK;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 题目：TopK问题的O(n)解法。BFPRT算法
 * 思想： 基本思想是找到第k小的数，然后遍历一遍数组，找到比k小的即可。
 * 简单的可以直接用快排的划分思想找到第K小的数，但是时间复杂度不满足。
 * BFPRT算法： 将数组先划分为n/5个数组，求每一个子数组的中位数，把中位数添加到一起得到一个中位数数组，然后求中位数数组的中位数，
 * 并根据这个中位数划分整个数组，如果最后这个中位数在数组中的k位置，那么就找到了，如果比k小，就在左边找，不然就在右边找。
 */
public class BFPRT {
    public static int[] topK(int[] arr, int k) {
        int[] copyArr = Arrays.copyOfRange(arr, 0, arr.length);
        //第k小的数
        int num = BFPRT(copyArr, 0, copyArr.length - 1, k);
        int[] topK = new int[k];
        int index = 0;
        for (var e : arr) {
            if (e < num) {
                topK[index++] = e;
            }
        }
        for (; index < topK.length; index++) {
            topK[index] = num;
        }
        return topK;
    }

    static int pivotPartition(int[] arr,int left,int right) {
        int temp = arr[left];
        while(left<right){
            while(left<right){
                if(arr[right]<temp){
                    arr[left] = arr[right];
                    break;
                }else {
                    right--;
                }
            }
            while (left<right){
                if(arr[left]>temp){
                    arr[right] = arr[left];
                    break;
                }else {
                    left++;
                }
            }
        }
        arr[left] = temp;
        return left;
    }

    //找中位数
    public static int finMidNum(int[] arr, int begin, int end) {
        int midIndex = (begin + end) / 2;
        int left = begin;
        int right = end;
        //一次划分的结果
        int pivot;
        while (true) {
            pivot = pivotPartition(arr, left, right);
            if (pivot == midIndex) {
                break;
            }
            //pivot偏小
            if (pivot < midIndex) {
                left = pivot + 1;
            } else {
                right = pivot - 1;
            }
        }
        return arr[pivot];
    }

    public static int BFPRT(int[] arr, int begin, int end, int k) {
        int len = end - begin + 1;
        int[] midArr = new int[len % 5 == 0 ? len / 5 : len / 5 + 1];
        for (int curBegin = begin, curEnd = Math.min(end, begin + 4), index = 0; curEnd <= end && curBegin <= curEnd; curBegin += 5, curEnd = Math.min(end, curEnd + 5)) {
            midArr[index++] = finMidNum(arr, curBegin, curEnd);
        }
        //找到中位数数组的中位数
        int mid = finMidNum(midArr, 0, midArr.length - 1);
        //根据中位数将数组划分,并返回中位数的位置
        int partition = partition(arr, mid, begin, end);
        if (k == partition) {
            return arr[k];
        } else if (k < partition) {
            return BFPRT(arr, begin, partition - 1, k);
        }else{
            return BFPRT(arr, partition + 1, end, k);
        }
    }

    //根据中位数划分整个数组
    public static int partition(int[] arr, int partitionNum, int begin, int end) {
        int left = begin - 1;
        int right = end + 1;
        int index = begin;
        while (index < right) {
            if (arr[index] < partitionNum) {
                swap(arr, ++left, index++);
            } else if (arr[index] > partitionNum) {
                swap(arr, index, --right);
            } else {
                index++;
            }
        }
        //返回中位数的位置。
        return left + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 9, 6, 4, 5};
        System.out.println(partition(arr, 5, 0, arr.length - 1));
        System.out.println(finMidNum(arr,5,8));
        System.out.println(Arrays.toString(topK(arr,6)));
    }
}
