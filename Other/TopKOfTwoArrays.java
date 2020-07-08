package Other;

/**
 * 题目：在两个有序数组中找到第K小的数
 */
public class TopKOfTwoArrays {

    //在两个长度相等的有序数组中查找中位数。
    public static int findMedianInTwoArrays(int[] arr1, int[] arr2, int arr1Left, int arr1Right, int arr2Left, int arr2Right) {
        if (arr1.length == 1) {
            return Math.min(arr1[0], arr2[0]);
        }
        int arr1Mid = 0;
        int arr2Mid = 0;
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

    public static int solution(int[] arr1, int[] arr2, int k) {
        //arr1若较长，交换两个数组，让arr2代表较长数组
        if (arr1.length > arr2.length) {
            int[] temp = arr1;
            arr1 = arr2;
            arr2 = temp;
        }
        int arr1Len = arr1.length;
        int arr2Len = arr2.length;
        // k小于较短数组的长度
        if (k <= arr1Len) {
            return findMedianInTwoArrays(arr1, arr2, 0, k - 1, 0, k - 1);
        }
        // k大于较长数组的长度
        else if (k > arr2Len) {
            int arr1Begin = k - arr2Len - 1;
            //那么arr[arr1Begin]这个数前刚好就有k-1个数。
            if (arr1[arr1Begin] >= arr2[arr2Len - 1]) {
                return arr1[arr1Begin];
            }
            int arr2Begin = k - arr1Len - 1;
            if (arr2[arr2Begin] >= arr1[arr1Len - 1]) {
                return arr2[arr2Begin];
            }
            return findMedianInTwoArrays(arr1, arr2, arr1Begin + 1, arr1Len - 1, arr2Begin + 1, arr2Len - 1);
        }
        //k位于较短和较长数组之间
        else {
            if (arr2[k - arr1Len - 1] >= arr1[arr1Len - 1]) {
                return arr2[k - arr1Len-1];
            }
            return findMedianInTwoArrays(arr1, arr2, 0, arr1Len - 1, k - arr1Len, k - 1);
        }

    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3, 4, 5};
        int[] arr2 = new int[]{1, 3, 9, 10};
        System.out.println(solution(arr1, arr2, 7));
    }
}
