package 笔试汇总.同城58;

import java.util.*;

public class Main2 {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
    }
    public ArrayList<ArrayList<Integer>> printNode (TreeNode node) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(node);
        int toBeVisited = 1;
        int nextLevel = 0;
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            list.add(cur.val);
            if (cur.left != null) {
                queue.offer(cur.left);
                nextLevel++;
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                nextLevel++;
            }
            toBeVisited--;
            if (toBeVisited == 0) {
                toBeVisited = nextLevel;
                nextLevel = 0;
                Collections.sort(list);
                res.add(list);
                list = new ArrayList<>();
            }
        }
        return res;
    }
}
