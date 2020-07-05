package GreedyAlgorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 题目：拼接所有字符串产生字典顺序最小的大写字符串
 * 题目分析：思路肯定是排序，但是大小如何比较？ 两个字符串str1,str2，应该str1+str2和str2+str1的大小。
 */
public class ConcatString{
    public static int compare(String s, String t1) {
        String str1 = s.concat(t1);
        String str2 = t1.concat(s);
        return str1.compareTo(str2);
    }

    public static void main(String[] args) {
        String[] strArr = {"abc","de","b","ba"};
        Arrays.sort(strArr,ConcatString::compare);
        StringBuilder stringBuilder = new StringBuilder();
        for(var str:strArr){
            stringBuilder.append(str);
        }
        System.out.println(stringBuilder.toString());
    }

}
