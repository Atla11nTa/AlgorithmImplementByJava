package DynamicProblem;

/**
 * 题目：丢棋子问题
 * 题目描述： 大楼有N层，地面为0层，棋子有K个，现通过丢棋子的方式找到棋子不会摔碎的最高层数，试求出最差情况下的最少尝试次数。
 * 解题思路： 特殊情况，大楼只有0层，那么尝试次数是0;若棋子只有一个，最差情况就是一楼一楼的试，到最后一楼才出结果，尝试N次。
 * 方法P（N，k）表示N层楼，k个棋子的结果。
 * 从i楼开始尝试，若碎了，就执行P(i-1,k-1)，尝试低楼，若i楼没碎，就执行P(N-i，k)，尝试上面的那些楼。
 * 最差结果，就是max(P(i-1,k-1),P(N-i,k))。
 * 最少尝试数，就是从1尝试到N楼，选择一个最少的尝试结果。
 *
 */
public class Chess {
    public static int solution(int N, int kChess) {
        if (N < 1 || kChess < 1) {
            return 0;
        }
        return process1(N, kChess);
    }

    //暴力递归解法,复杂度高
    public static int process1(int N, int kChess) {
        if (N == 0) {
            return 0;
        }
        if (kChess == 1) {
            return N;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            min = Math.min(min, Math.max(process1(i - 1, kChess - 1), process1(N - i, kChess)));
        }
        return min;
    }

    //dp解法,复杂度低
    public static int process2(int N, int kChess) {
        if (kChess == 1) {
            return N;
        }
        //dp[i][j]表示 i楼j个棋子的尝试数
        int[][] dp = new int[N + 1][kChess + 1];
        //一个棋子尝试数都是i
        for (int i = 1; i < dp.length; i++) {
            dp[i][1] = i;
        }
        //填表
        for (int i = 1; i <= N; i++) {
            for (int j = 2; j <= kChess; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 1; k < i + 1; k++) {
                    min = Math.min(min, Math.max(dp[i - 1][j], dp[i - 1][j - 1]));
                }
                dp[i][j] = min + 1;
            }
        }
        return dp[N][kChess];
    }

    public static void main(String[] args) {

    }
}
