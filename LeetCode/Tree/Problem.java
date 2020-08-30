package LeetCode.Tree;

import com.sun.source.tree.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Problem {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();
        int toPrint = 1;
        int nextLevel = 0;
        boolean leftToRight = true;
        while (!deque.isEmpty()) {
            TreeNode cur = deque.poll();
            if (leftToRight) {
                curList.add(cur.val);
            } else {
                curList.add(0, cur.val);
            }
            if (cur.left != null) {
                deque.offer(cur.left);
                nextLevel++;
            }
            if (cur.right != null) {
                deque.offer(cur.right);
                nextLevel++;
            }
            toPrint--;
            if (toPrint == 0) {
                res.add(curList);
                curList = new ArrayList<>();
                leftToRight = !leftToRight;
                toPrint = nextLevel;
                nextLevel = 0;
            }
        }
        return res;
    }
}
