package ArrayAndMatrix;
import java.util.Arrays;

/**
 * 题目： 求数组/矩阵的子数组/子矩阵的最大累加和
 * 思路分析： 数组解法不解释了
 * 矩阵求法：一个三重循环，第一个循环：i表示i行，第二个循环：j表示j行，j行从i行开始。第三个循环：k表示k列。
 * 从j行开始往下，逐渐的把同一列元素累加，组成数组，然后就变成了问题的求解了。
 *
 */

public class MaxSum {
    //数组
    public static int problem1(int[] arr) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (sum + arr[i] >= 0) {
                sum += arr[i];
                maxSum = Math.max(sum,maxSum);
            }else {
                //归零
                sum = 0;
            }
        }
        return maxSum;
    }

    //矩阵
    public static int problem2(int[][] matrix) {
        int maxSum = Integer.MIN_VALUE;
        int[] curArr = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            Arrays.fill(curArr, 0);
            for (int j = i; j < matrix.length; j++) {
                int cur = 0;
                for (int k = 0; k < matrix[0].length; k++) {
                    curArr[k] += matrix[j][k];
                    cur += curArr[k];
                    cur = Math.max(cur, 0);
                    maxSum = Math.max(maxSum,cur);
                }
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, -2, 3, 5, -2, 6, -1};
        System.out.println(problem1(arr));
    }
}
