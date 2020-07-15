package LeetCode;

import java.util.*;

/**
 * 题目： 47.含重复数字的全排列
 * Set转List， List<> iii = new ArrayList(set)
 */
public class Problem47 {
    public static void permute(Integer[] nums, Set<List<Integer>> res, int index) {
        if (index == nums.length - 1) {
            res.add(new ArrayList<>(Arrays.asList(nums)));
        } else {
            for (int i = index; i < nums.length; i++) {
                Integer temp = nums[index];
                nums[index] = nums[i];
                nums[i] = temp;
                permute(nums, res, index + 1);
                temp = nums[index];
                nums[index] = nums[i];
                nums[i] = temp;
            }
        }
    }

    public static List<List<Integer>> solution(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Integer[] num = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            num[i] = nums[i];
        }
        permute(num, res, 0);
        return new ArrayList<>(res);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};
        System.out.println(solution(nums));
    }
}
