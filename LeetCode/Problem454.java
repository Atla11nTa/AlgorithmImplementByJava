package LeetCode;

import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.Map;

/**
 * 题目: 454. 四数相加 II
 * 思路: 先把其中两个数组相加的结果A+B保存在哈希表中, 然后计算遍历其他两个数组之和C+D,看哈希表中是否存在-(C+D)
 * 思路比较巧妙
 */
public class Problem454 {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        int lenA = A.length;
        int lenB = B.length;
        int lenC = C.length;
        int lenD = D.length;
        for (var a : A) {
            for (var b : B) {
                map.put(a + b, map.getOrDefault(a + b, 0) + 1);
            }
        }
        int res = 0;
        Integer count = 0;
        int CD;
        for (var c : C) {
            for (var d : D) {
                CD = c + d;
                count = map.get(-CD);
                if (count != null) {
                    res += count;
                }
            }
        }
        return res;
    }
}
