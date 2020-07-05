package StringProblem;

import java.util.Scanner;

/**
 * 题目：最长重复子串长度
 * 题目描述: 举例： “xabcabcx” -> 6，"xabcabcabcx"-> 6
 * 题目分析：注意，题目问的是一次重复，对于多次重复不算。
 */

public class MaxRepeatSubString {
    public static int Solution(char[] str){
        int maxLen = 0;
        int len = str.length;
        //用i表示子串长度
        for(int i=1;i<len/2;i++) {
            int count = 0;
            for(int j=0;j<len-i;j++){
                //第j位与第j+i位是相等的
                if(str[j] == str[j+i])
                    count++;
                //若不等，说明不是重复串，长度归0
                else
                    count = 0;
                //找到一个长度为i的重复串
                if(count == i){
                    maxLen = Math.max(maxLen,i*2);
                    break;
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        char[] strArr = str.toCharArray();
        int maxlen = Solution(strArr);
        System.out.println(maxlen);
    }
}
