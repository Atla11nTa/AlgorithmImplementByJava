package LeetCode;

/**
 * 题目: 334. 递增的三元子序列
 * 思路: 很巧秒的思路.
 * first: 记录一元子序列的尾
 * second: 记录二元子序列的尾
 * 遍历数组,若是小于first,那么就更新first, 若是小于second就更新second, 大于second就是答案.
 * 二元组的第一个数就是first, 所以num如果比second小, num可能比first大吗? 不可能, first相当于一个过滤器.
 * 以此思路可以求4元组/5元组....层层过滤即可
 */

public class H_Problem334 {
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int num : nums) {
            // first相当于一个过滤器
            if (num <= first) {
                first = num;
            }
            // num一定是比first大的, 二元组的第一个数是first.
            else {
                if (num <= second) {
                    second = num;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
