package Backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 排列问题
 * 相关题目： leetcode:46,47,60
 */
public class Permute {
    /**
     * 求全排列(无重复数字)
     * 因为元素先后顺序相关，所以每次从所有集中挑选一个，但是为了避免重复挑选，使用一个bool数组记录已选的元素。
     * 另外一种类似的思路是，固定一位，然后把后面的每一位依次与前面交换(剑指offer解法)
     */
    static void backTrack(Integer[] nums, boolean[] isUsed, List<List<Integer>> res, List<Integer> curList, int start) {
        if (curList.size() == nums.length) {
            res.add(new ArrayList<>(curList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (isUsed[i]) {
                    continue;
                }
                curList.add(nums[i]);
                isUsed[i] = true;
                backTrack(nums, isUsed, res, curList, start + 1);
                curList.remove(curList.size() - 1);
                isUsed[i] = false;
            }
        }
    }
    /**
     * 求全排列（存在重复数字）
     * 先排序, 主要在剪枝的操作。
     */
    static void backTrack2(Integer[] nums, boolean[] isUsed, List<List<Integer>> res, List<Integer> curList, int start) {
        if (curList.size() == nums.length) {
            res.add(new ArrayList<>(curList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                //因为存在重复，所以先排序，若与上一个元素相同，上一个元素没加入结果集，本次也跳过。
                if (i > 0 && nums[i - 1].equals(nums[i]) && isUsed[i]) {
                    continue;
                }
                curList.add(nums[i]);
                isUsed[i] = true;
                backTrack2(nums, isUsed, res, curList, start + 1);
                curList.remove(curList.size() - 1);
                isUsed[i] = false;
            }
        }
    }
}
