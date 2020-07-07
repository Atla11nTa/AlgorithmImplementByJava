package Other;

/**
 * 题目：在有序旋转数组中找到一个数
 * 题目描述： 有序数组arr，可能经过一个旋转处理，例如有序数组[1,2,3,4,5,6,7]旋转后[4,5,6,7,1,2,3]。要求时间复杂度O(logn)
 * 思路：二分查找的变形，每次先判断左右哪侧有序。
 * 假如左侧有序，利用有序的信息，先查看k是不是在左侧有序部分，若在有序部分，下一次查找就在左边进行，反之在右侧进行。
 */
public class FindInRotateArr {
    public static boolean solution(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (arr[mid] == k) {
                return true;
            }
            //右侧有序
            if (arr[mid] < arr[right]) {
                //检查是不是在右边的有序部分
                if (k > arr[mid] && k <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                //检查是不是在左边的有序部分
                if (k >= arr[left] && k < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 6, 7, 1, 2, 3};
        System.out.println(solution(arr, 8));
    }
}
