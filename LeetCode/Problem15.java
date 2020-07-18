package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目： 15.三数之和
 * 地址： https://leetcode-cn.com/problems/3sum/
 * 思路：
 * 思路1： 双指针，和两数之和思路类似，先排序，然后固定一个数，在剩下的部分找剩余的两数
 * 两数之和： 一个left在头，一个right在尾，left左移，right右移。找到一个答案后，还要继续找。
 * 时间复杂度: O(n^2)
 *
 * 思路2： 回溯法（超时）。
 * 先排序，然后回溯的选择三次。但是如何去重一些技巧。
 * 为方便去重和剪枝，先排序。以[-4,-1,-1,0,1,2]为例，若不去重，那么最后会输出[-1,-1,2],[-1,0,1][-1,0,1]
 * 可以看到，两个-1出现在重复结果中，如果去重呢？ 使用boolean[] isVisited保存每个位置是否被挑选，注意回溯后要复原。
 * 第二个-1选不选？如果上一个被选了，那么就可以选，但如果上一个没被选，说明不需要，也不选。
 */
public class Problem15 {
    public void twoSum(int[] nums, int left, int right, int target, List<List<Integer>> resList) {
        int sum;
        while (left < right) {
            sum = nums[left] + nums[right];
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(nums[left]);
                list.add(nums[right]);
                list.add(-target);
                resList.add(list);
                //去重
                do {
                    left++;
                } while (nums[left] == nums[left - 1] && left < right);
                do {
                    right--;
                } while (nums[right] == nums[right + 1] && left < right);
            }
        }
    }
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        Arrays.sort(nums);
        int left=0;
        int right = nums.length - 1;
        for (int i = 0; i <= nums.length - 3; i++) {
            //后面的和不可能等于0
            if (nums[i] > 0) {
                break;
            }
            //去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            left = i + 1;
            // 在后面找两数之和
            twoSum(nums, left, right, -nums[i], resList);
        }
        return resList;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-4,-1,-1,0,1,2};
        System.out.println(backTrack.threeSum(nums));

    }
}

class backTrack{
    static void back(List<List<Integer>> resList, List<Integer> curList, int[] nums, int start, boolean[] isVisited,int sum) {
        if (curList.size() == 3) {
            if (sum == 0) {
                resList.add(new ArrayList<>(curList));
            }
        } else {
            for (int i = start; i < nums.length; i++) {
                //这里去重的思想很巧妙，如果上一个相同的数没被选，那么这个也不该选。
                if (i > 0 && nums[i] == nums[i - 1] && !isVisited[i - 1]) {
                    continue;
                }
                if (sum + nums[i] > 0) {
                    break;
                }
                curList.add(nums[i]);
                isVisited[i] = true;
                back(resList, curList, nums, i + 1, isVisited, sum + nums[i]);
                curList.remove(curList.size() - 1);
                isVisited[i] = false;
            }
        }
    }
    static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] isVisited = new boolean[nums.length];
        back(resList, new ArrayList<>(), nums, 0, isVisited, 0);
        return resList;
    }
}