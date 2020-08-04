package 笔试汇总.网易2020校招笔试Java开发;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 时间复杂度超出
 */
public class Main4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
//        int[] nums = new int[n];
        int[] checks = new int[m];
        int[] res = new int[m];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        for (int i = 0; i < n; i++) {
            maxHeap.offer(in.nextInt());
        }
        for (int i = 0; i < m; i++) {
            checks[i] = in.nextInt();
        }
        int[] temp = new int[n];
        for (int i = 0; i < m; i++) {
            int checkNum = checks[i];
            int count = 0;
            while (!maxHeap.isEmpty() &&checkNum <= maxHeap.peek()) {
                int max = maxHeap.poll();
                temp[count] = max - 1;
                count++;
            }
            for (int j = 0; j < count; j++) {
                maxHeap.offer(temp[j]);
            }
            res[i] = count;
        }
        for (int r : res) {
            System.out.println(r);
        }

    }
}
