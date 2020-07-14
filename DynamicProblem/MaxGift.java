package DynamicProblem;

/**
 * 题目：礼物的最大价值
 */
public class MaxGift {
    public static int solution(int[][] map) {
        int[][] dp = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = map[i][j];
                    continue;
                }
                if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + map[i][j];
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + map[i][j];
                    continue;
                }
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + map[i][j];
            }
        }
        return dp[map.length - 1][(map[0].length) - 1];
    }

    public static void main(String[] args) {
        int[][] map = new int[][]{{1, 10, 3, 8}, {12, 2, 9, 6}, {5, 7, 4, 11}, {3, 7, 16, 5}};
        System.out.println(solution(map));
    }
}
