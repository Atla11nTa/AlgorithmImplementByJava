package 笔试汇总.奇安信;

import java.util.Scanner;

public class Main2 {
    public static int maxCandies (int[] candies, int[] coin, int n) {
        // write code here
        int left=0,right=0;
        int maxCandis=0;
        for (int i=0;i<n;i++){
            if (coin[i]==1){
                maxCandis += candies[i];
            }
            right++;
        }
        int len = coin.length;
        int curCandis = maxCandis;
        int maxCandisIndex = 0;
        while (right < len) {
            if (coin[right]==1){
                curCandis += candies[right];
            }
            if (coin[left]==1){
                curCandis -= candies[left];
            }
            left++;
            right++;
            if (curCandis > maxCandis) {
                maxCandis = curCandis;
                maxCandisIndex = left;
            }
        }
        int res = 0;
        for (int i = 0; i < len; i++) {
            if (i == maxCandisIndex){
                int count = 0;
                while (count < n && i < len) {
                    count++;
                    res += candies[i];
                    i++;
                }
            }
            if (i >= len) {
                break;
            }
            if (coin[i] == 0) {
                res += candies[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] candis = new int[]{
                3, 5, 7, 2, 8, 8, 15, 3
        };
        int[] coins = new int[]{1,0,1,0,1,0,1,0};
        System.out.print(maxCandies(candis, coins, 3));
    }

}
