package LeetCode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 题目: 350两个数组的交集
 * 思路: 别把题目想难了,题目的意思是求出两个数组都存在的元素,并不要求两个数组的连续子数组.
 */
public class Problem35 {
    public static int[] intersect(int[] nums1, int[] nums2) {
        //nums2较短
        if (nums1.length < nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        //记录nums2中每个元素出现的次数
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            hashMap.put(nums2[i], hashMap.getOrDefault(nums2[i], 0) + 1);
        }
        int[] res = new int[nums2.length];
        int index = 0;
        for (var num : nums1) {
            int count = hashMap.getOrDefault(num, 0);
            if (count > 0) {
                res[index++] = num;
                count--;
                if (count > 0) {
                    hashMap.put(num, count);
                } else {
                    hashMap.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(res, 0, index);
    }
}
