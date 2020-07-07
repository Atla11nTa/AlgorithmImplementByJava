package DynamicProblem;

/**
 * 题目：正数数组的最小不可组成和
 * 题目描述：数组arr，其所有子集相加结果的最小值min，最大值max，min-max之间的所有正整数，求最小无法被子集相加和表示的。
 * 例如： arr= {3,2,5}，min = 2，max = 10, 2-10区间中，4,6,9无法被子集相加得到，所以最小是4,如果min-max之间的数都能表示，那就返回max+1;
 *
 * 解题思路：arr累加和为sum，也就是max，那么子集可以表示的整数范围在0-sum之间(准确的说应该是min-sum之间)，一共sum+1个数
 * 用dp数组记录这sum+1能否被表示: dp[i]=true表示i这个数能被表示。
 * 二重循环，第一个循环参数i表示第i个元素。
 * 第二个参数表示，arr[i]到sum之间的数,因为这是正数数组，所以arr[i]开始的子集和一定在arr[i]~sum之间。
 * 如果dp[j-arr[i]]为true，那么arr[j]也一定为true，否则保持不变。
 */
public class UnFormedSum {
    public static int solution(int[] arr) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
            sum += arr[i];
        }
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 0; i < arr.length; i++) {
            for (int j = sum; j >= arr[i]; j--) {
                dp[j] = dp[j - arr[i]] ? true : dp[j];
            }
        }
        for (int i = min; i <= sum; i++) {
            if (!dp[i]) {
                return i;
            }
        }
        return sum + 1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 4};
        System.out.println(solution(arr));
    }
}
