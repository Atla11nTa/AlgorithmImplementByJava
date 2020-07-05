package StringProblem;

/**
 * 题目：最小包含子串的长度
 * 题目描述：给定字符串str1和str2，给出str1中包含str2全部字符的最小子串长度，若不存在返回0。
 * 例如： str1 = “abcde” str2 = "ac" 返回3。
 * 解法分析：“滑动窗口”算法。
 * 关键结构： 1. 滑动窗口： left和right标记。
 *          2. matchLen: 记录当前窗口中的匹配长度。
 *          3. hashMap：记录组成str2的每个字符剩余所需个数。比如 "a" -> 1，表示还需要一个a, “b” -> -1，表示当前窗口里多余一个“b”。
 * 思路： 1. 先移动right，当matchLen等于str2的长度时，说明当前窗口里包含了str2的全部字符，但是有多余的可以删除。
 *       2. 若left指向的字符在hashMap中值小于0说明是多余的，移动left，并且还原hashMap。当left指向的字符在hashMap中等于0,说明不能再移动窗口了，
 *       记录当前窗口长度。
 *       3. 移动一下left，回到1过程继续其他可能的情况。
 */
public class ShortestSubString {
    public static int Solution(String str1, String str2){
        char[] str1Arr = str1.toCharArray();
        char[] str2Arr = str2.toCharArray();
        int minLen = Integer.MAX_VALUE;
        //当前滑动窗口中匹配的总个数
        int matchLen = 0;
        int left = 0;
        int right = 0;
        //hashMap里的value表示组成str2剩余所需的字符个数。为负数，表示是多余不需要的。
        int[] hashMap = new int[256];
        for(var ch:str2Arr){
            hashMap[ch]++;
        }
        char ch;
        while (left<str1Arr.length && right<str1Arr.length){
            ch = str1Arr[right];
            hashMap[ch]--;
            //说明ch是组成str2所需的，matchLen++
            if(hashMap[ch] >= 0){
                matchLen++;
            }
            //到right为止，已经全部匹配了，开始移动left缩短窗口的长度，删减左边不必要的部分
            if(matchLen == str2.length()){
                //小于0,说明是多余的，移动left，并且还原hashMap
                while (hashMap[str1Arr[left]] < 0){
                    hashMap[str1Arr[left]]++;
                    left++;
                    //退出循环时，left指向首个必须字符。
                }
                //计算最短长度
                minLen = Math.min(minLen, right - left + 1);
                //进行下一次匹配应该从left的下一个开始，所以移动左窗口，记得还原。
                hashMap[str1Arr[left]]++;
                left++;
                matchLen--;
            }
            right++;
        }
        return minLen==Integer.MAX_VALUE?0:minLen;
    }

    public static void main(String[] args) {
        String str1 = "123445";
        String str2 = "344";
        System.out.println(Solution(str1,str2));
    }
}
