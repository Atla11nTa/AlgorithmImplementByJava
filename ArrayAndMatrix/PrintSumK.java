package ArrayAndMatrix;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 题目：不重复打印排序数组中相加为给定值的所有二元组和三元组
 * 思路分析：二元组：设置两个指针，一个在头，一个在尾，移动这两个指针，判断和
 * 三元组： 先固定一个数，然后在这个数之后找三者之和为给定值的二元组。
 */
public class PrintSumK {
    //打印二元组
    static void solution1(int[] arr ,int k) {
        int left = 0;
        int right = arr.length - 1;
        ArrayList<Integer[]> res = new ArrayList<>();
        int sum;
        while (left < right) {
            //不重复打印
            if ((left > 0 && arr[left] == arr[left - 1])) {
                left++;
                continue;
            } else if ((right < arr.length - 1 && arr[right] == arr[right + 1])) {
                right--;
                continue;
            }
            sum = arr[left] + arr[right];
            if (sum == k) {
                res.add(new Integer[]{arr[left], arr[right]});
                left++;
                right--;
            } else if (sum < k) {
                left++;
            } else {
                right--;
            }
        }
        res.forEach(e->{
            System.out.println(e[0]+" "+e[1]);
        });
    }

    //打印三元组
    static void solution2(int[] arr,int k) {

    }

    public static void main(String[] args) {
        int[] arr = new int[]{-8, -4, -3, 0, 1, 1, 2, 4, 5, 8, 9};
        solution1(arr,10);
        solution2(arr, 10);
    }
}
