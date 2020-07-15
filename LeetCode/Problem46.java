package LeetCode;

import java.util.*;

/**
 * 题目： 46.全排列
 * 解题思路：
 * 思路1：暴力回溯法，每次从nums中选择一个数字，下一次从剩余数字中挑选，选择n次。这种方法时间复杂度较高，主要是不好除去已选择的数字，每次需要遍历找到一个未选择的数字。
 * 思路2：巧妙递归。每次index分别与index之后的所有数字进行交换，递归进行，最终得到全排列。记住这个思路。
 */
public class Problem46 {
    public static void permute(Integer[] nums, List<List<Integer>> res, int index) {
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
        List<List<Integer>> res = new ArrayList<>();
        Integer[] num = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            num[i] = nums[i];
        }
        permute(num, res, 0);
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};
        System.out.println(solution(nums));
    }
}
