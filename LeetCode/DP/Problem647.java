package LeetCode.DP;



import java.util.concurrent.*;

/**
 * 题目：647.回文子串
 * 思路： dp求解即可。
 */
public class Problem647 {
    public static int countSubstring(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        boolean[][] dp = new boolean[len][len];
        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j >= 0; j--) {
                if (j == i) {
                    dp[j][i] = true;
                    res++;
                } else if (j == i - 1 && chars[i] == chars[j]) {
                    dp[j][i] = true;
                    res++;
                } else if ( chars[i] == chars[j] && dp[j + 1][i - 1]){
                    dp[j][i] = true;
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(countSubstring("aaaaa"));
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return null;
            }
        };
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
        Thread t1 = new Thread(futureTask);
        t1.start();

    }
}
