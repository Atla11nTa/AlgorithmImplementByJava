package 笔试练习汇总.Shopee2019校招部分编程题汇总;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main1 {
    static class Pos{
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object object) {
            Pos pos = (Pos) object;
            return this.x == pos.x && this.y == pos.y;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(this.x) & Integer.hashCode(this.y);
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        int n = in.nextInt();
        Set<Pos> hashSet = new HashSet<>(n);
        for (int i = 0; i < n; i++) {
            hashSet.add(new Pos(x - in.nextInt(), in.nextInt()));
        }
        long[] dp = new long[y + 1];
        dp[0] = 1;
        Pos curPos = new Pos(0, 0);
        for (int i = x; i >= 0; i--) {
            for (int j = 0; j <= y; j++) {
                curPos.x = i;
                curPos.y = j;
                if (!hashSet.contains(curPos)) {
                    if (j > 0) {
                        dp[j] = dp[j] + dp[j - 1];
                    }
                } else {
                    dp[j] = 0;
                }
            }
        }
        System.out.println(dp[y]);
    }
}
