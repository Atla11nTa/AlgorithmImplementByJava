package StringProblem;

import java.util.Arrays;

/**
 * 题目：字符数组所有字符是否都只出现一次
 * 题目描述： eg. chars = ['a','b','c'] return true; chars = ['1','2','1'] return false;
 * Solution1: 时间复杂度O(N)。 哈希
 * Solution2: 空间复杂度O(1)。思路：先排序，然后遍历，所以需要实现一个额外空间复杂度为O(1)的排序算法。只有非递归的堆排序可以满足条件
 */
public class isUnique {
    public static boolean Solution1(char[] chars){
        int[] hashMap = new int[256];
        Arrays.fill(hashMap,0);
        for(var v:chars){
            if(++hashMap[v] > 1){
                return false;
            }
        }
        return true;
    }

    public static boolean Solution2(char[] chars){
        if(chars.length <= 1)
            return true;
        Arrays.sort(chars);
        for(int i = 1;i<chars.length;i++){
            if(chars[i] == chars[i-1])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        char[] chars = new char[]{'a','b','c'};
        System.out.println(Solution1(chars));
        System.out.println(Solution2(chars));
    }
}
