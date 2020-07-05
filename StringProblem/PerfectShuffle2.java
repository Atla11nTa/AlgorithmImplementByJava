package StringProblem;

import java.nio.file.Path;
import java.util.Arrays;

/**
 * 题目：完美排序进阶
 * 题目描述：比如数组中有五个数字，调整成[a,b,c,d,e]，满足a <= b >= c <= d >= e
 * 题目要求：时间复杂度O(nlogn)，空间复杂度O(1)
 * 分析：对于一个数组[1,2,3,4]。如何才能满足要求呢？其实就是完美洗牌 [1,3,2,4]
 * 对于一个有序数组，前半区比较小，后半区比较大，所以就将后半区的数依次插入前半区即实现。
 * 如果数组长度为奇数，就忽略第一个数，对剩下的数进行完美排序
 */

public class PerfectShuffle2 {
    private static void Swap(int[] arr,int i1,int i2){
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }
    private static  void InitHeap(int[] arr){
        int len = arr.length;
        for(int i=len-1;i>=0;i--){
            int child = i;
            int parent = (child-1) / 2;
            while(parent >= 0){
                if(arr[parent] < arr[child])
                {
                    Swap(arr,parent,child);
                    child = parent;
                    parent = (child-1) / 2;
                }else {
                    break;
                }
            }
        }
    }
    private static void RebuildHeap(int[] arr,int left,int right){
        int parent = left;
        int child = left * 2 + 1;
        while(child <= right){
            if(child + 1 <= right && arr[child] < arr[child+1]){
                child++;
            }
            if(arr[parent] < arr[child]){
                Swap(arr,parent,child);
                parent = child;
                child = parent * 2 + 1;
            }
            else
                break;
        }
    }
    private static void HeapSort(int[] arr){
        int len = arr.length;
        InitHeap(arr);
        //依次将堆首与无序部分的最后一个元素交换，然后重建堆
        for(int i = len - 1;i>=1;i--){
            //先交换
            Swap(arr,i,0);
            //剩下部分重建堆
            RebuildHeap(arr,0,i-1);
        }
    }

    public static void Solution(int[] arr){
        //未满足时间复杂度要求，先进行堆排序
        HeapSort(arr);
        //长度为偶数
        if(arr.length % 2 == 0){
            PerfectShuffle.Solution(arr,0,arr.length-1);
        }else {
            PerfectShuffle.Solution(arr,1,arr.length-1);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,4,1,2,5,6};
        Solution(arr);
        System.out.println(Arrays.toString(arr));
    }
}
