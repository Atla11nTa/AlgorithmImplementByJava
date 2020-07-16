package LeetCode;

/**
 * 题目: 485.最大连续1的个数
 */
public class Problem485 {
    public static int solution(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                res = Math.max(count, res);
                count = 0;
            }
        }
        res = Math.max(count, res);
        return res;
    }

    public static void main(String[] args) {
        System.out.print(solution(new int[]{1,1,0,1,1,1}));
    }
}
