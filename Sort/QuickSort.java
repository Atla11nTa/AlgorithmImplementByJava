package Sort;

import java.util.Arrays;
import java.util.Enumeration;

public class QuickSort {
    public static void quickSort(int[] arr,int left,int right){
        if(left>=right)
            return;
        int begin = left;
        int end = right;
        int temp = arr[left];
        while (left<right){
            while (left<right){
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
                }else{
                    left++;
                }
            }
        }
        arr[left] = temp;
        quickSort(arr,begin,left-1);
        quickSort(arr,right+1,end);
    }
    public static void Solotion(int[] arr){
        quickSort(arr,0,arr.length-1);
    }
    public static void partition(int[] arr,int left,int right){
        if (left >= right) {
            return;
        }
        int begin = left;
        int end = right;
        int temp = arr[left];
        while (left < right) {
            while (left < right) {
                if (arr[right] < temp) {
                    arr[left] = arr[right];
                    break;
                } else {
                    right--;
                }
            }
            while (left < right) {
                if (arr[left] > temp) {
                    arr[right] = arr[left];
                    break;
                } else {
                    left++;
                }
            }
        }
        arr[left] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3,2,4,1,5,3,8,6};
//        Solotion(arr);
        partition(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
