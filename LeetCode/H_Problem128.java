package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 *题目： 128. 最长连续序列
 * 地址： https://leetcode-cn.com/problems/longest-consecutive-sequence/
 * 要求：时间复杂度O(n)
 *
 * 解题思路：
 * 思路1(一开始的思路)： 先求数组的最大最小值，然后建立一个哈希表，大小就是max-min+1, 表示可能出现的数字，用哈希表记录每个数字是否出现，最后遍历
 * 哈希表统计连续出现的长度。
 * 缺点：空间复杂度太大，空间浪费很严重，而且
 * 思路2： 先用哈希表统计数组中的数字集，然后统计以数组中每个元素x开头的连续序列，通过哈希表查找x+i是否存在, 达到O(n^2)的时间复杂度。
 * 优化方法： 举例说明，[5,4,1,3]
 * 按照刚才的思路，需要先查找5,5+i... 之后又查找4,4+i... 最后查找3,3+i... 存在很多重复查找。
 * 其实在考察num时，若数字集中存在num-1，那么就可以跳过num，因为在考察num-1时会涉及到num。
 * 以上为例，考察数字3时，就会得到连续序列3,4,5；就包含了考察数字4,5的情况。
 */
public class H_Problem128 {
    public static int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (var num : nums) {
            numSet.add(num);
        }
        int longest = 0;
        for (int num : nums) {
            if (!numSet.contains(num - 1)) {
                //统计以num开头的连续序列长度
                int currentNum = num;
                int currentLen = 1;
                // num+i存在，len++
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentLen++;
                }
                longest = Math.max(longest, currentLen);
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2147483646,-2147483647,0,2,2147483644,-2147483645,2147483645};
        System.out.println(longestConsecutive(nums));

    }
}
