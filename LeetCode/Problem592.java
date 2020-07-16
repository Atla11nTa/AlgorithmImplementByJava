package LeetCode;

import java.util.ArrayList;

/**
 * 题目:592.分数加减问题
 * 链接: https://leetcode-cn.com/problems/fraction-addition-and-subtraction/
 * 思路: 先分离出分子分母,然后根据分母通分,把分子相加,最后求分子分母的最小公约数,约分得到结果.
 * 分离分子分母这一步最复杂.
 */
public class Problem592 {
    public static int maxDivisor(int a, int b) {
        int c = a % b;
        while (c != 0) {
            a = b;
            b = c;
            c = a % b;
        }
        return b;
    }

    public static String fractionAddition(String expression) {
        String[] strs = expression.split("/");
        StringBuilder resStr = new StringBuilder();
        int[] upNums = new int[20];
        int[] downNums = new int[20];
        int numCounts = 0;
        char[] strArr = expression.toCharArray();
        boolean isUp = true;
        for (int i = 0; i < strArr.length; ) {
            //接下来一个数是负数
            if (strArr[i] == '-') {
                StringBuilder numStr = new StringBuilder();
                numStr.append('-');
                i++;
                while (i<strArr.length &&strArr[i] >= '0' && strArr[i] <= '9') {
                    numStr.append(strArr[i]);
                    i++;
                }
                int num = Integer.parseInt(numStr.toString());
                //属于分子
                if (isUp) {
                    upNums[numCounts] = num;
                } else {
                    downNums[numCounts] = num;
                    numCounts++;
                    isUp = true;
                }
            }
            else if (strArr[i] == '/') {
                isUp = false;
                i++;
            } else if (strArr[i] == '+') {
                i++;
                StringBuilder numStr = new StringBuilder();
                while (i<strArr.length &&strArr[i] >= '0' && strArr[i] <= '9') {
                    numStr.append(strArr[i]);
                    i++;
                }
                int num = Integer.parseInt(numStr.toString());
                //属于分子
                if (isUp) {
                    upNums[numCounts] = num;
                } else {
                    downNums[numCounts] = num;
                    numCounts++;
                    isUp = true;
                }
            } else {
                StringBuilder numStr = new StringBuilder();
                while (i<strArr.length &&strArr[i] >= '0' && strArr[i] <= '9') {
                    numStr.append(strArr[i]);
                    i++;
                }
                int num = Integer.parseInt(numStr.toString());
                //属于分子
                if (isUp) {
                    upNums[numCounts] = num;
                } else {
                    downNums[numCounts] = num;
                    numCounts++;
                    isUp = true;
                }
            }
        }

        int down = 1;
        for (int i = 0; i < numCounts; i++) {
            down *= downNums[i];
        }
        int up = 0;
        for (int i = 0; i < numCounts; i++) {
            up += down / downNums[i] * upNums[i];
        }
        if (up < 0 || down < 0) {
            resStr.append('-');
        }

        if (up == 0) {
            down = 1;
        } else {
            int maxDiv = maxDivisor(up, down);
            up = up / maxDiv;
            down = down / maxDiv;
        }
        resStr.append(Math.abs(up));
        resStr.append('/');
        resStr.append(Math.abs(down));
        return resStr.toString();
    }

    public static void main(String[] args) {
        System.out.println(fractionAddition("-5/2+10/3+7/9"));
    }
}
