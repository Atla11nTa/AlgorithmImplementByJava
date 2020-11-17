package 笔试汇总.VIVO;

import java.util.*;

public class Main3 {
    // 拓扑排序
    public static String compileSeq (String input) {
        // write code here
        String[] next = input.split(",");
        int n = next.length;
        List<List<Integer>> nextList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nextList.add(new ArrayList<>());
        }
        // 前驱计数
        int[] preCount = new int[n];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < next.length; i++) {
            int nextId = Integer.parseInt(next[i]);
            // 添加后继
            if (nextId != -1 && nextId != i) {
                nextList.get(nextId).add(i);
                preCount[i]++;
            } else {
                queue.offer(i);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            stringBuilder.append(cur).append(",");
            for (int nex : nextList.get(cur)) {
                if(--preCount[nex]==0){
                    queue.offer(nex);
                }
            }
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }
    public static void main(String[] args) {
        System.out.print(compileSeq("1,2,-1,1"));
    }
}
