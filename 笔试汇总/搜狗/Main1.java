package 笔试汇总.搜狗;

import 笔试练习汇总.腾讯校园招聘2020.Main;

public class Main1 {
    class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    Interval[][][] memo;
    public Interval dfs(int start,char[] chars1,char[] chars2,int n,int cur,int rightCount) {
        if (start < n && memo[start][cur][rightCount] != null) {

            return memo[start][cur][rightCount];
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Interval temp;
        if (start == n) {
            if (rightCount == 0) {
                min = Math.min(min, cur);
                max = Math.max(max, cur);
            }
        } else {
            // 如果当前答案正确数大于0
            if (rightCount > 0) {
                // 二者答案相同
                if (chars1[start] == chars2[start]) {
                    // AB均对
                    temp = dfs(start + 1, chars1, chars2, n, cur + 1, rightCount - 1);
                    min = Math.min(min, temp.start);
                    max = Math.max(max, temp.end);
                    // AB均错
                    temp = dfs(start + 1, chars1, chars2, n, cur, rightCount);
                    min = Math.min(min, temp.start);
                    max = Math.max(max, temp.end);
                } else {
                    // A对B错
                    temp = dfs(start + 1, chars1, chars2, n, cur + 1, rightCount);
                    min = Math.min(min, temp.start);
                    max = Math.max(max, temp.end);
                    // A错B错
                    temp = dfs(start + 1, chars1, chars2, n, cur, rightCount);
                    min = Math.min(min, temp.start);
                    max = Math.max(max, temp.end);
                    // A错B对
                    temp = dfs(start + 1, chars1, chars2, n, cur, rightCount - 1);
                    min = Math.min(min, temp.start);
                    max = Math.max(max, temp.end);
                }
            } else {
                // 二者答案相同
                if (chars1[start] == chars2[start]) {
                    // AB均错
                    temp = dfs(start + 1, chars1, chars2, n, cur, rightCount);
                    min = Math.min(min, temp.start);
                    max = Math.max(max, temp.end);
                } else {
                    // A对B错
                    temp = dfs(start + 1, chars1, chars2, n, cur + 1, rightCount);
                    min = Math.min(min, temp.start);
                    max = Math.max(max, temp.end);
                    // A错B错
                    temp = dfs(start + 1, chars1, chars2, n, cur, rightCount);
                    min = Math.min(min, temp.start);
                    max = Math.max(max, temp.end);
                }
            }
        }
        memo[start][cur][rightCount] = new Interval(min, max);
        return memo[start][cur][rightCount];
    }

    public Interval solve (int n, int k, String str1, String str2) {
        // 维度1代表当前位置，维度2代表当前正确数，维度3代表A剩余正确数
        memo = new Interval[n+1][n+1][n+1];
        // write code here
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        return dfs(0, chars1, chars2, n, 0, k);
    }

    public static void main(String[] args) {
        Main1 m = new Main1();
        String str1 = "ABC";
        String str2 = "ABC";



    }
}
