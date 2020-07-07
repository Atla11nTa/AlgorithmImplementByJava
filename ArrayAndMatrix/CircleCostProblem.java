package ArrayAndMatrix;

import java.util.Arrays;

/**
 * 题目： 加油站良好出发点问题
 * 题目描述： 所有加油站构成一个环，数组oil记录了每个加油站的油可以跑的距离，数组dis保存每个加油站到下一个加油站的距离，如果从加油站i出发能回到i
 * 说明加油站i是良好加油站。返回一个bool数组，记录每个加油站是否为良好加油站
 */
public class CircleCostProblem {
    public static boolean[] solution(int[] oil, int[] dis) {
        boolean[] isGood = new boolean[oil.length];
        //i表示起点
        for (int i = 0; i < oil.length; i++) {
            int curOil = 0;
            //j表示当前位置
            int j = i;
            while (true){
                curOil += oil[j];
                if (curOil >= dis[j]) {
                    curOil -= dis[j];
                } else {
                    isGood[i] = false;
                    break;
                }
                j = j == oil.length - 1 ? 0 : j + 1;
                if (j == i) {
                    isGood[i] = true;
                    break;
                }
            }
        }
        return isGood;
    }

    public static void main(String[] args) {
        int[] oil = new int[]{4, 5, 3, 1, 5, 1, 1, 9};
        int[] dis = new int[]{1, 9, 1, 2, 6, 0, 2, 0};
        System.out.println(Arrays.toString(solution(oil, dis)));
    }
}
