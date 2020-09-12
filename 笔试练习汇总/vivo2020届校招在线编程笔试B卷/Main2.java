package 笔试练习汇总.vivo2020届校招在线编程笔试B卷;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        int input[] = parseInts(inputStr.split(" "));
        String output = solution(input);
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

    private static String solution(int[] input) {
        int n = input[0];
        int m = input[1];
        StringBuilder stringBuilder = new StringBuilder();
        boolean[] isOut = new boolean[n];
        int cur = 0;
        for (int i = 0; i < n; i++) {
            int index = 0;
            while (index < m) {
                while (isOut[cur]) {
                    cur++;
                    if (cur == n) {
                        cur = 0;
                    }
                }
                cur++;
                if (cur == n) {
                    cur = 0;
                }
                index++;
            }

            if (cur == 0) {
                isOut[n - 1] = true;
                stringBuilder.append(n).append(" ");
            } else {
                isOut[cur - 1] = true;
                stringBuilder.append(cur).append(" ");
            }
        }
        return stringBuilder.toString().trim();
    }
}
