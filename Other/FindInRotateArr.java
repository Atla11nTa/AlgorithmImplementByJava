package Other;

/**
 * 题目：在有序旋转数组中找到一个数
 * 题目描述： 有序数组arr，可能经过一个旋转处理，例如有序数组[1,2,3,4,5,6,7]旋转后[4,5,6,7,1,2,3]。要求时间复杂度O(logn)
 * 思路：二分查找的变形，每次先判断左右哪侧有序。
 * 假如左侧有序，利用有序的信息，先查看k是不是在左侧有序部分，若在有序部分，下一次查找就在左边进行，反之在右侧进行。
 * 题目2: 在有序旋转数组中找最小值
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

    //找有序旋转数组的最小值

    /**
     * 思路: 旋转数组的特点: 旋转后mid以右的某部分是有序的
     */
    public int minArray(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            // mid<right, 那么mid以右是有序的, 最小值只可能出现在mid及其左边
            if (numbers[mid] < numbers[right]) {
                right = mid;
            }
            // mid>right, 那么右侧是乱序的,就是说原本有序数组的左部分旋转到了mid后, 而最小值也在这部分.
            else if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            }
            // mid == right, 说明有重复,此时就不能随意的删除一半元素了, 但可以使right--,因为right有替代品mid, 即使是最小值, 也不影响结果.
            else {
                right--;
            }
        }
        return numbers[left];
    }
    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 6, 7, 1, 2, 3};
        System.out.println(solution(arr, 8));
    }
}
