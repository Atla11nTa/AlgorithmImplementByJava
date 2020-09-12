package 笔试练习汇总.猿辅导2020校招笔试2;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 题目： 猿辅导课堂上老师提供了一些角色，学生可以从中选择一个自己喜欢的角色扮演，每3个不同的角色就可以组成一个小组，进行分组对话。
 * 当老师点击开始分组对话按钮的时候，服务器会为已经选择自己角色的同学分配对话小组，请问最多能组成多少个对话小组？
 * 思路： 利用大顶堆存储，每次选堆顶的三个数，然后结果加上第三大的数num3，再让剩余两个数减num3之后入堆。但是这种写法是会造成浪费的。
 * 比如3 3 3 3，按这种思路，先取3 3 3，剩下3 0 0 0 ，结果是3。但是3 3 3 3 -> 3 2 2 2 ->2 1 1 2 -> 1 0 1 1 -> 0 0 0 0，这种取法可以取出4组
 * 所以就是每次最大的三个数减一然后入队。再优化一下的话，就是每次让num3等于num4-1，这样就不会造成浪费。
 */

public class Main3 {
    public static long getRes(int[] nums, int n) {
        long res = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        for (int i = 0; i < n; i++) {
            priorityQueue.offer(nums[i]);
        }
        while (priorityQueue.size() >= 3) {
            int num1 = priorityQueue.poll();
            int num2 = priorityQueue.poll();
            int num3 = priorityQueue.poll();
            res++;
            if (--num3 > 0) {
                priorityQueue.offer(num3);
            }
            if (--num1 > 0) {
                priorityQueue.offer(num1);
            }
            if (--num2 > 0) {
                priorityQueue.offer(num2);
            }
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseNum = in.nextInt();
        long[] res = new long[caseNum];
        for (int i = 0; i < caseNum; i++) {
            int n = in.nextInt();
            int[] nums = new int[n];
            for (int j = 0; j < n; j++) {
                nums[j] = in.nextInt();
            }
            res[i] = getRes(nums, n);
        }
        for (int i = 0; i < caseNum; i++) {
            System.out.println(res[i]);
        }
    }
}
