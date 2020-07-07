package Other;

/**
 * 题目：从N个数中等概率打印M个数
 * 题目分析：关键在于等概率，其实就是古典概型，袋子里取多个球，只要取了不放回，那么就是等概率的。
 * 所以关键在于打印过的数字之后不能再打印。不重新申请空间的做法就是把打印后的移到尾部。
 */
public class RandomPrintM {
    public static void solution(int[] arr,int M) {
        if (M > arr.length - 1) {
            return;
        }
        int index = 0;
        for (int i = 0; i < M; i++) {
            index = (int) (Math.random() * (arr.length - i - 1));
            System.out.println(arr[index]);
            swap(arr, index, arr.length - i - 1);
        }
    }

    static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        solution(arr, 5);
    }
}
