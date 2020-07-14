package ArrayAndMatrix;

/**
 * 题目：数组中未出现的最小正整数
 * 思路1： 时间空间复杂度均为O(N)
 * 数组长度为N，所求的数范围就在1~N之间。
 * 遍历数组，用一个哈希表存储已经出现了的数字，已经出现了，就把那一位置1, 最后遍历哈希表，最先为0的位置就是所要求的数。
 *
 */
public class MinestPositiveNum {
    public static int solution(int[] arr) {
        int[] hash = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0 && arr[i] <= arr.length) {
                hash[arr[i]-1] = 1;
            }
        }
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] == 0) {
                return i + 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 1, 4, 6, 5, 2};
        System.out.println(solution(arr));
    }
}
