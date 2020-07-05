package BitOperation;

/**
 * 题目：在其他数都出现K次的数组中找到只出现一次的数
 * 题目分析：对于两个k进制数A,B, 两个数做不进位的相加，那么位i的结果 = (A[i]+B[i]) % k，所以当k个k进制不进位相加时，每一位 = (K*A)%k= 0.
 * 根据这个性质，先将数组中每一个数转换为k进制数，然后做不进位加法，最后剩下的就是所要求的那个数，将这个数转为十进制数即可。
 */
public class FindOnceNum {
    //将一个数转换为k进制数
    public static int[] numToKNum(int num, int k) {
        int[] res = new int[32];
        int index = 0;
        while (num != 0) {
            res[index++] = num % k;
            num /= k;
        }
        return res;
    }
    //把一个k进制数转换为10进制数
    public static int kNumToNum(int[] arr, int k) {
        int res = 0;
        int kn = 1;
        for (int i = 0; i < arr.length; i++) {
            res += kn * arr[i];
            kn *= k;
        }
        return res;
    }

    public static int solution(int[] arr, int k) {
        int[] e = new int[32];
        int[] curNum;
        for (int i = 0; i < arr.length; i++) {
            curNum = numToKNum(arr[i], k);
            for (int j = 0; j < curNum.length; j++) {
                e[j] = (e[j] + curNum[j]) % k;
            }
        }
        return kNumToNum(e, k);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1,2,  3, 3, 4, 4};
        System.out.println(solution(arr, 2));
    }
}
