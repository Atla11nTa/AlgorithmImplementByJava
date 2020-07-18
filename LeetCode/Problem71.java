package LeetCode;

import java.util.*;

/**
 * 题目: 71. 简化路径
 * 地址: https://leetcode-cn.com/problems/simplify-path/
 * 思路: 先用"/"分割字符串, 从后到前检查每个盘符, 若是.或者"" 就跳过, 若是".."表示要跳到上一级,记录".."的数目.
 */
public class Problem71 {
    public static String simplifyPath(String path) {
        String[] dirs = path.split("/");
        List<String> list = new LinkedList<>();
        int count = 0;
        for(int i = dirs.length - 1; i >= 0; i--){
            switch (dirs[i]) {
                case "":
                case ".":
                    break;
                case "..": //记录..的数目
                    count++;
                    break;
                default:
                    if (count > 0) {
                        count--;
                    } else {
                        //..小于等于0时才将这个路径加入, 因为是从后往前遍历,所以头插.
                        list.add(0,dirs[i]);
                    }
            }

        }
        return "/" + String.join("/", list);
    }

    public static void main(String[] args) {
        System.out.print(simplifyPath("/home//foo/"));
    }
}
