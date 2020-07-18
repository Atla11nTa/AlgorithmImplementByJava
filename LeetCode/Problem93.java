package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目: 93. 复原IP地址
 * 地址: https://leetcode-cn.com/problems/restore-ip-addresses/
 * 思路: 回溯法, 每次选择一位/两位/三位数字, 一共选择四次.
 */
public class Problem93 {
    public static void BackTrack(char[] charArr, List<String> res,List<String> current, int start) {
        if (current.size() == 4) {
            if (start == charArr.length) {
                res.add(String.join(".", current));
            }
        } else {
            String next;
            //分别选择一位/两位/三位
            for (int count = 1; count <= 3; count++) {
                if (start + count > charArr.length) {
                    break;
                }
                next = String.valueOf(charArr, start, count);
                //多位数字不能以0开头
                if (count >= 2 && next.charAt(0) == '0') {
                    continue;
                }
                //三位不超超过255
                if (count == 3 && Integer.parseInt(next) > 255) {
                    continue;
                }
                current.add(next);
                BackTrack(charArr, res, current, start + count);
                current.remove(current.size() - 1);
            }
        }
    }
    public static List<String> restoreIpAddresses(String s) {
        char[] charArr = s.toCharArray();
        List<String> res = new ArrayList<>();
        BackTrack(charArr, res, new ArrayList<>(), 0);
        return res;
    }

    public static void main(String[] args) {
        System.out.print(restoreIpAddresses("25525511135"));
    }

}
