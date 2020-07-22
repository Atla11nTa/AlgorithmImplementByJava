package LeetCode;

import java.util.*;

/**
 * 题目: 8. 字符串转换整数 (atoi)
 * 思路1: 直接计算, 注意分析边界条件.首先判断符号位,然后截取首部的数字部分,之后去除首部的连续0, 在之后进行转换, 溢出判断.
 * 思路2: DFA有限状态机.先设计状态集,和转换关系.
 * 当前状态                   下一状态
 * 状态           空格         数字         其他符号
 * start         start       number         end
 * signed        end         number         end
 * number        end         number         end
 */

public class H_DFA {
    int sign = 1;
    long res = 0;
    String state = "start";
    Map<String, List<String>> stateMap = new HashMap<>();
    H_DFA() {
        stateMap.put("start", new ArrayList<String>(Arrays.asList("start","signed", "inNumber", "end")));
        stateMap.put("signed", new ArrayList<>(Arrays.asList("end","end","inNumber", "end")));
        stateMap.put("inNumber", new ArrayList<>(Arrays.asList("end","end","inNumber", "end")));
    }

    String getNextState(char ch) {
        if (ch == ' ') {
            return stateMap.get(state).get(0);
        }
        if (ch == '+' || ch == '-') {
            return stateMap.get(state).get(1);
        }
        if (ch >= '0' && ch <= '9') {
            return stateMap.get(state).get(2);
        }
        return stateMap.get(state).get(3);
    }

    int myAtoi(String str) {
        char[] chars = str.toCharArray();
        for (var ch : chars) {
            state = getNextState(ch);
            if (state.equals("inNumber")) {
                res = res * 10 + ch - '0';
                res = sign == 1 ? Math.min(res, (long)Integer.MAX_VALUE) : Math.min(res, -(long)Integer.MIN_VALUE);
            } else if (state.equals("signed")) {
                sign = ch == '-' ? -1 : 1;
            } else if (state.equals("end")) {
                break;
            }
        }
        return sign * (int)res;
    }

    public static void main(String[] args) {
        H_DFA HDfa = new H_DFA();
        System.out.println(HDfa.myAtoi("-42"));

    }
}
