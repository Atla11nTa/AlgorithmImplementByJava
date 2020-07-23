package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 题目:350.两个数组的交集II
 * 思路:
 * 思路1: 哈希表
 * 思路2: 先排序,然后使用双指针,分别指向两个数组的两端, 然后移动.
 */
public class Problem350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            hashMap.put(nums1[i], hashMap.getOrDefault(nums1[i], 0) + 1);
        }
        List<Integer> resList = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            Integer count = hashMap.get(nums2[i]);
            if (count != null && count > 0) {
                resList.add(nums2[i]);
                hashMap.put(nums2[i], count - 1);
            }
        }
        int[] res = new int[resList.size()];
        int index = 0;
        for (var num : resList) {
            res[index++] = num;
        }
        return res;
    }
}
