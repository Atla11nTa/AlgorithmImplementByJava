package StringProblem;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayRotate {
    public static void Solution(int[] arr,int left, int mid, int right){
        if (left < right)
        {
            ArrayReverse(arr,left,mid);
            ArrayReverse(arr,mid+1,right);
            ArrayReverse(arr,left,right);
        }
    }

    private static void ArrayReverse(int []arr,int left,int right){
        while (left<right){
            int temp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = temp;
        }
    }
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6};
        Solution(arr,0,1,5);
        System.out.println(Arrays.toString(arr));
    }
}
