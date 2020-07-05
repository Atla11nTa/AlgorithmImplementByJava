package Sort;

import java.util.Arrays;

/**
 * 题目：几乎有序
 * 题目描述：给一个无序数组，长度为N，N为偶数，使得排序后，前N/2个数的最大值小于等于后N/2个数的最小值，这就叫“几乎有序”
 * 解法分析：其实就是找中位数，让中位数处于数组中间，左边是比他小的数，右边是比他大的数。
 * 这个过程其实就是快排的partition。因为快排的一次partition操作就是让pos左边的数都小于他，右边的数都大于他。
 * 所以若pos来到了数组中间，那么就完成了“几乎有序”，若还没有，就进入下一次快排
 */

public class NearlySort {
    public static int partition(int[] arr,int left,int right){
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
    public static int FindMid(int[] arr){
        int mid=0;
        int midIndex = arr.length/2;
        int midIndex2 = arr.length/2+1;
        int left = 0;
        int right = arr.length-1;
        int pos = 0;
        while(true){
            pos = partition(arr,left,right);
            if(pos == midIndex || pos == midIndex2)
                break;
            else if (pos>midIndex){
                right = pos-1;
            }else
                left = pos+1;
        }
        return midIndex;
    }

    public static void Solution(int[] arr){
        int mid = FindMid(arr);
        System.out.println(mid);
    }

    public static void main(String[] args) {
        int[] arr = {3,1,2,4,4,2};
        Solution(arr);
        System.out.println(Arrays.toString(arr));
    }
}
