package 笔试汇总.猿辅导;

import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        List<Integer> leftEdge = new ArrayList<>();
        List<Integer> rightEdge = new ArrayList<>();
        List<List<Integer>> leafList = new ArrayList<>();
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        int toVisit = 1;
        int nextLevel = 0;
        int level = 1;
        List<Integer> curLevelLeaf = new ArrayList<>();
        while (!queue.isEmpty()) {
            int index = queue.poll();
            int leftChildIndex = (index + 1) * 2 - 1;
            int rightChildIndex = (index + 1) * 2;
            // 一层的开始，进左边界。
            if (nextLevel == 0&&(leftChildIndex < n || rightChildIndex < n)) {
                leftEdge.add(index);
            }

            if (leftChildIndex < n) {
                queue.offer(leftChildIndex);
                nextLevel++;
            }
            if (rightChildIndex < n) {
                queue.offer(rightChildIndex);
                nextLevel++;
            }
            if (leftChildIndex >= n && rightChildIndex >= n) {
                curLevelLeaf.add(index);
            }
            toVisit--;
            // 一层打印结束，进右边界
            if (toVisit == 0) {
                toVisit = nextLevel;
                nextLevel = 0;
                if (level != 1 && (leftChildIndex < n || rightChildIndex < n)) {
                    rightEdge.add(0, index);
                }
                leafList.add(curLevelLeaf);
                curLevelLeaf = new ArrayList<>();
                level++;
            }
        }
        for (int num : leftEdge) {
            System.out.print(nums[num] + " ");
        }
        for (int i = leafList.size() - 1; i >= 0; i--) {
            curLevelLeaf = leafList.get(i);
            for (int num : curLevelLeaf) {
                System.out.print(nums[num] + " ");
            }
        }
        for (int num : rightEdge) {
            System.out.print(nums[num] + " ");
        }
    }
}
