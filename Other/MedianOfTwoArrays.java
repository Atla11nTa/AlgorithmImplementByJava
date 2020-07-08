package Other;

/**
 * 题目：求两个长度相等的有序数组的共同中位数
 * 要求：时间复杂度O(logn)，求上中位数
 * 思路分析： 根据时间复杂度和有序，使用二分查找。
 * 把数组长度分为奇数和偶数分别考虑。
 * 数组长度为奇数：
 * arr1 = [1,2,3,4,5] arr2 = [1'.2',3',4',5']。
 * 两个数组总长10, 上中位数在位置5上。
 * 1. mid1 == mid2, 在mid1前有1,2,1',2'四个数，4,5和4',5'都可以确定在其后，所以中位数就是mid1。
 * 2. mid1 < mid2，在mid2 3'前至少有1,2,3,1‘,2'，其中1',2'与1,2,3的相对位置不清楚，4,5也可能在3'的前面。
 * 所以5号位置可能是3/4/5/1‘/2’/, 需要进一步查找，为了保持这两个数组长度仍然相同，所以下一次在[3,4,5]和[1',2',3']中查找中位数。
 * 3. mid1 > mid2。与步骤相同。
 * 数组长度为偶数：
 * arr1 = [1,2,3,4] ,arr2 = [1',2',3',4']。
 * 数组总场为8,上中位数在位置4上。
 * 1. mid1 == mid2， 那么在mid1前确定有1,1‘，所以四号位置就是mid1或者mid2。
 * 2. mid1<mid2，那么在mid2 2‘前至少有1,2,1'，其中1'和1，2的相对位置不清楚，四号位置可能是3, 4, 1',2'，所以下一次查找就在[3,4]和[1',2']中
 * 3. mid1>mid2同理。
 */
public class MedianOfTwoArrays {
    public static double solution(int[] arr1, int[] arr2) {
        if (arr1.length == 1) {
            return Math.min(arr1[0], arr2[0]);
        }
        int arr1Left = 0;
        int arr1Mid = 0;
        int arr1Right = arr1.length - 1;
        int arr2Left = 0;
        int arr2Mid = 0;
        int arr2Right = arr2.length - 1;
        while (arr1Left < arr1Right) {
            arr1Mid = (arr1Left + arr1Right) / 2;
            arr2Mid = (arr2Left + arr2Right) / 2;
            if (arr1[arr1Mid] == arr2[arr1Mid]) {
                return arr1[arr1Mid];
            }
            //arr1中位数较大。
            if (arr1[arr1Mid] < arr2[arr2Mid]) {
                //当前长度为偶数
                if ((arr1Right - arr1Left + 1) % 2 == 0) {
                    arr1Left = arr1Mid + 1;
                    arr2Right = arr2Mid;
                } else {
                    arr1Left = arr1Mid;
                    arr2Right = arr2Mid;
                }
            }
            //arr2的中位数较大
            else {
                //当前长度为偶数
                if ((arr2Right - arr2Left + 1) % 2 == 0) {
                    arr2Left = arr2Mid + 1;
                    arr1Right = arr1Mid;
                } else {
                    arr2Left = arr2Mid;
                    arr1Right = arr1Mid;
                }
            }
        }
        return Math.min(arr1[arr1Left], arr2[arr2Left]);
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3, 4, 5};
        int[] arr2 = new int[]{4, 7, 8, 9, 10};
        System.out.println(solution(arr1,arr2));
    }
}
