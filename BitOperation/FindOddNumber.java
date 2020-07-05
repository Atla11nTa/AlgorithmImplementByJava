package BitOperation;

public class FindOddNumber {
    /**
     * 题目1：在其他数都出现偶数次的数组中找到出现奇数次的数字
     * 题目分析：通过异或的特点，n^n = 0，n^0 = n。而且异或运算满足交换律和结合律。
     * 假设数组中有n1,n2,n3,其中n2出现一次。那么 n1^n2^n3^n1^n3 = n2.
     */
    public static int solution(int[] arr) {
        int n = 0;
        for (var num : arr) {
            n ^= num;
        }
        return n;
    }

    /**
     * 题目2：数组中两个数出现了奇数次，其他都出现偶数次，找到这两个数。
     * 思路分析：数组所有元素异或的结果为e,根据异或的性质，e = A^B，其中A,B为出现奇数次的两个数。
     * 因为A和B不同，那么e任意为1的位，A和B的这一位不同，假设A的这一位为1，那么B这一位必为0。通过(e&-e)取得e的非0位，rightOne
     * 1. 将数组中每一个数与rightOne相与，不为0的数 ^ n，因为除了AB其他数都出现偶数次，不影响异或结果，所以最后的n就是A,B中的某个数。
     * 2. 因为e = A^B , 所以另一个数 = e ^ n。
     */
    public static void solution2(int[] arr) {
        int e = 0, n = 0;
        for (var num : arr) {
            e ^= num;
        }
        int rightOne = e & (~e + 1);
        for (var num : arr) {
            if ((num & rightOne) != 0) {
                n ^= num;
            }
        }
        System.out.println(n + "\n" + (n^e));
    }
    public static void main(String[] args) {
        int[] arr = new int[]{1,1,2,3,2,5,5};
        int[] arr2 = new int[]{1,1,2,2,3,4,5,5};
        System.out.println(solution(arr));
        solution2(arr2);
    }
}


