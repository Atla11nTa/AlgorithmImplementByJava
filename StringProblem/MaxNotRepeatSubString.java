package StringProblem;

import java.util.Arrays;

/**
 * 题目：找到字符串的最长无重复字符子串
 * 题目描述： 给定一个字符串str，返回str的最长无重复子串的长度，要求时间复杂度O(N)。
 * 题目分析：使用一个哈希表存储字符出现次数，当字符出现不止0次时，哈希表清0,然后比较一次长度。
 */
public class MaxNotRepeatSubString {
    public static int solution(String str) {
        char[] strArr = str.toCharArray();
        int maxLen = Integer.MIN_VALUE;
        int curLen = 0;
        int[] hashMap = new int[256];
        char ch;
        for(int i=0;i<strArr.length;i++){
            ch = strArr[i];
            if(hashMap[ch]==0){
                curLen++;
                hashMap[ch]++;
            }
            //出现重复字符，哈希表归0.
            else{
                maxLen = Math.max(curLen,maxLen);
                Arrays.fill(hashMap,0);
                curLen = 1;
                hashMap[ch] = 1;
            }
        }
        maxLen = Math.max(curLen,maxLen);
        return maxLen;
    }

    public static void main(String[] args) {
        String str = "abcd";
        System.out.println(solution(str));
    }
}
