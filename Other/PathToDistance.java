package Other;

import java.util.Arrays;

/**
 * 题目： 路径数组变为统计数组
 */
public class PathToDistance {
    public static int[] solution(int[] path) {
        int[] distance = new int[path.length];
        //先找首都
        int capital = 0;
        for (int i = 0; i < path.length; i++) {
            if (path[i] == i) {
                capital = i;
            }
        }
        for (int i = 0; i < path.length; i++) {
            if(i == capital){
                continue;
            }
            //城市i的上一个城市
            int j = path[i];
            //记录距离
            int count = 1;
            while (j != capital) {
                count++;
                j = path[j];
            }
            distance[count]++;
        }
        distance[0] = 1;
        return distance;
    }

    public static void main(String[] args) {
        int[] path = new int[]{9, 1, 4, 9, 0, 4, 8, 9, 0, 1};
        System.out.println(Arrays.toString(solution(path)));
    }
}
