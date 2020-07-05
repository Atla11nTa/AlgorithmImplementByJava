package StringProblem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 问题：判断两个字符串是否互为变形词
 * 题目描述：给定两个字符串str1和str2，如果两个字符串出现的字符种类一样且每种字符出现的次数也一样，即为变形词
 *  解决方法：利用哈希，使用int[256]表示，分别遍历str1和str2，最后若哈希表所有元素的值都是0,说明为true
 */
public class isDeformation {
    // 使用hashMap集合
    public static boolean Solution(String str1, String str2){
        HashMap<Character,Integer> hashMap = new HashMap<>();
        for(int i=0;i<str1.length();i++){
            Character atI = str1.charAt(i);
            hashMap.put(atI,hashMap.getOrDefault(atI,0)+1);
        }
        for(int i=0;i<str2.length();i++){
            Character atI = str2.charAt(i);
            hashMap.put(atI,hashMap.getOrDefault(atI,0)-1);
        }
        for(var v:hashMap.values()){
            if(v != 0){
                return false;
            }
        }
        return true;
    }

    //使用数组表示hashMap
    public static boolean Solution2(String str1, String str2){
        int[] hashMap = new int[256];
        Arrays.fill(hashMap,0);
        for(int i=0;i<str1.length();i++){
            hashMap[str1.charAt(i)]++;
        }
        for(int i=0;i<str2.length();i++){
            hashMap[str2.charAt(i)]--;
        }
        for(var v:hashMap){
            if(v != 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String str1 = "123";
        String str2 = "231";
        System.out.println(Solution2(str1,str2));
    }
}
