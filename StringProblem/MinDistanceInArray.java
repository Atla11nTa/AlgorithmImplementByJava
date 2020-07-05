package StringProblem;

import java.util.ArrayList;

/**
 * 题目：找到两个字符串在字符串数组中的最小距离
 * 题目描述: 给定一个字符串数组arr = {“1”，“2”，“2”，“2”，“1”，“3”，“3”}， str1 = "1",str2 = "3"，所以最小距离是1
 */
public class MinDistanceInArray {
    public static int Solution(String[] arr, String str1, String str2) {
        if(arr == null || arr.length <= 2 || str1== null || str2 == null)
            return -1;
        int minDistance = Integer.MAX_VALUE;
        ArrayList<Integer> str1Index = new ArrayList<>();
        ArrayList<Integer> str2Index = new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            if(arr[i].equals(str1)){
                str1Index.add(i);
            }
            else if(arr[i].equals(str2)){
                str2Index.add(i);
            }
        }
        if(str1Index.size() == 0 || str2Index.size() == 0){
            return -1;
        }else {
            for(var i : str1Index){
                for (var j:str2Index){
                    minDistance = Math.min(Math.abs(i - j), minDistance);
                }
            }
        }
        return minDistance;
    }

    public static void main(String[] args) {
        String[] arr = {"1","3","3","3","2","3","1"};
        String str1 = "1";
        String str2 = "4";
        int minDistance = Solution(arr,str1,str2);
        System.out.println(minDistance);
    }
}
