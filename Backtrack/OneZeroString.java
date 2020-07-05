package Backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目： 0左边必有1的二进制字符串数量
 * 题目描述： 返回长度为N的所有二进制字符串，要求每个0的左边一位必须是1。
 * 题目分析： 固定的长度，固定的备选集，典型的回溯问题，每次先看当前是什么，然后选择下一次回溯的答案
 */
public class OneZeroString {
    public static List<String> Solution(int N){
        List<String> res = new ArrayList<>();
        backTrack(res,new char[N],0,N);
        return res;
    }
    public static void backTrack(List<String> res,char[] curString,int currentLen, int N){
        if(currentLen == N){
            res.add(String.valueOf(curString));
            return;
        }else {
            //只能以1开头
            if (currentLen == 0){
                curString[currentLen] = '1';
                backTrack(res,curString,1,N);
            }
            else {
                //当前是0,下一个必须是1
                if (curString[currentLen - 1] == '0') {
                    curString[currentLen] = '1';
                    backTrack(res, curString, currentLen + 1, N);
                }
                //当前是1,下一个随便
                else if (curString[currentLen - 1] == '1') {
                    curString[currentLen] = '1';
                    backTrack(res, curString, currentLen + 1, N);
                    curString[currentLen] = '0';
                    backTrack(res, curString, currentLen + 1, N);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<String> res = Solution(5);
        System.out.println(res.toString());
    }
}
