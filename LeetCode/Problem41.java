package LeetCode;

/**
 * 题目： 41. 缺失的最小正数
 */
public class Problem41 {
    public static int solution(int[] nums) {
        int res = 1;
        int max = nums.length;
        boolean[] hashMap = new boolean[nums.length + 2];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] <=max) {
                hashMap[nums[i]] = true;
            }
        }
        for (int i = 1; i < hashMap.length; i++) {
            if (!hashMap[i]) {
                return i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        System.out.println(solution(arr));
    }
}
