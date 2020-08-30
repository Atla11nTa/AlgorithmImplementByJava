package 笔试练习汇总.腾讯校园招聘2020;

import java.util.Scanner;
import java.util.Stack;

/**
 * 题目：
 小Q在周末的时候和他的小伙伴来到大城市逛街，一条步行街上有很多高楼，共有n座高楼排成一行。
 小Q从第一栋一直走到了最后一栋，小Q从来都没有见到这么多的楼，所以他想知道他在每栋楼的位置处能看到多少栋楼呢？（当前面的楼的高度大于等于后面的楼时，后面的楼将被挡住）
 思路： 单调栈

 */

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        // 先从右到左遍历记录能看到哦右边的数目
        // stack中要保存的是 能看见的楼的 index
        int[] rightLook = new int[n];
        Stack<Integer> stack = new Stack<>();
        for(int i = n - 1 ; i >= 0 ; i--){
            rightLook[i] = stack.size();
            while((!stack.isEmpty()) && (nums[i] >= nums[stack.peek()])){
                stack.pop();
            }
            stack.push(i);
        }
        stack.clear();
        //再从左到右遍历记录能看到左边的数目。
        for(int i = 0 ; i < n ; i++){
            int total = rightLook[i] + 1 + stack.size();
            while((!stack.isEmpty()) && (nums[i] >= nums[stack.peek()])){
                stack.pop();
            }
            System.out.print(total + " ");
            stack.push(i);
        }

    }
}
