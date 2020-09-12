package 笔试练习汇总.vivo2020届校招在线编程笔试B卷;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 题目：有条件的背包问题。
 * 题目描述： 将矿石分为两堆，使两堆矿石的重量相差尽量小，同时要求两堆矿石的数量相差不超过1.
 * 思路： dp[i]=true表示能将矿石分为重量为i的一堆， 那么另一堆理所当然是sum-i。
 * 仅求解能否分成靠上面就够了。
 * 额外使用一个list[Set]记录每个重量可以由多少个矿石相加得到。
 */

public class Main1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        int input[] = parseInts(inputStr.split(" "));
        int output = solution(input);
        System.out.println(output);
    }

    private static int[] parseInts(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return new int[0];
        }
        int[] intArr = new int[strArr.length];
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        return intArr;
    }

    private static int solution(int[] input) {
        int n = input.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += input[i];
        }
        Arrays.sort(input);
        boolean[] dp = new boolean[sum / 2 + 1];
        List<Set<Integer>> countList = new ArrayList<>(sum / 2 + 1);
        for (int i = 0; i < sum / 2 + 1; i++) {
            countList.add(new HashSet<>());
        }
        dp[0] = true;
        countList.get(0).add(0);
        for (int i = 0; i < n; i++) {
            for (int j = sum / 2; j >= input[i]; j--) {
                if (dp[j - input[i]]) {
                    dp[j] = true;
                    // 遍历
                    for (var count : countList.get(j - input[i])) {
                        countList.get(j).add(count + 1);
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = sum / 2; i >= 0; i--) {
            if (dp[i] && countList.get(i).contains(n / 2)) {
                res = Math.min(res, Math.abs(sum - i - i));
            }
        }
        return res;
    }
}
