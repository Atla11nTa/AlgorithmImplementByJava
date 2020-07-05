package StringProblem;

import java.util.Arrays;

/**
 * 题目：删除多余字符得到字典序最小的字符串
 * 题目描述： 字符串由小写字符组成。str = "acbc" -> “abc”. str = "baacbaccac" -> "abc"
 * 解题思路： 应从挑选字符出发，而不是排序。
 * 首先建立一个hashMap记录每中字符出现的次数，然后遍历字符串，当一种字符的频数为0时，说明该字符在之后不会再出现，所以现在要进行一次选择，在这
 * 之间选择一个字典序最小的字符，并且将之后所有的字符删除，完成一次挑选，接着进行下一个。
 * 举例： 1. "baacbaccac" ，遍历至"baacb"时，'b'不会再出现，这里字典序最小的是‘a’，挑选出来，并且删除'acbaccac'中的字符'a'。
 *       2. 得到‘cbccc’，接着继续进行步骤1
 */
public class RemoveDuplicateLetters {
    private static void CharCount(String str ,int[] hashMap){
        for(int i=0;i<str.length();i++){
            hashMap[str.charAt(i)]++;
        }
    }

    public static String Solution(String str){
        StringBuilder res = new StringBuilder();
        while(str.length()!=0){
            int[] hashMap = new int[128];
            Arrays.fill(hashMap,0);
            CharCount(str,hashMap);
            //找到字符最后一次出现的位置
            int lastCharIndex = 0;
            for(int i=0;i<str.length();i++){
                if(--hashMap[str.charAt(i)] == 0){
                    lastCharIndex = i;
                    break;
                }
            }
            //在[0...lastCharIndex]区域寻找字典序最小的字符
            char minChar = 'z';
            int minCharIndex = 0;
            for(int i=0;i <=lastCharIndex;i++){
                if(str.charAt(i)<minChar){
                    minChar = str.charAt(i);
                    minCharIndex = i;
                }
            }
            res.append(minChar);
            //从后半部分中删除字典序最小的字符
            StringBuilder newStrBuffer = new StringBuilder();
            for(int i=minCharIndex+1;i<str.length();i++){
                if(str.charAt(i) != minChar){
                    newStrBuffer.append(str.charAt(i));
                }
            }
            str = newStrBuffer.toString();
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String str = "acbc";
        str = Solution(str);
        System.out.println(str);
    }
}
