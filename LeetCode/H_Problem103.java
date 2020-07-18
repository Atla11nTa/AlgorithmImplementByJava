package LeetCode;

import java.util.*;

/**
 * 题目：103. 二叉树的锯齿形层次遍历
 * 地址： https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/submissions/
 * 主要还是二叉树层次遍历如何知道一层访问结束的问题。
 * 方法一： 在每层的后面往队列里加一个null作为区分，当访问到null节点时说明一层访问完了，这时如果队列里还有元素，这些元素都是下一层的元素，
 * 就再往队列里加一个null，如果队列里没有元素了，说明已经访问完最后一层，这时就不要再加null，否则会死循环。
 * 方法二：用两个变量记录本层剩余的节点数toBePrint和下一层的节点数nextLevel。当toBePrint为0时，说明一层访问结束，将nextLevel赋值给toBePrint，
 * nextLevel为0。
 *
 * 这题就是在层次遍历的基础上，记录一个是从左到右还是从右到左，按不同的顺序加入到结果集里。
 */
public class H_Problem103 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        Queue<TreeNode> queue =new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> levelList = new LinkedList<>();
        queue.offer(root);
//        queue.offer(null);
        boolean leftToRight = true;
        int nextLevel = 0;
        int toBePrint = 1;
        TreeNode cur;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            // 按从左到右依次加
            if (leftToRight)
                levelList.addLast(cur.val);
            // 往头加。
            else
                levelList.addFirst(cur.val);
            if (cur.left != null) {
                queue.offer(cur.left);
                nextLevel++;
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                nextLevel++;
            }
            toBePrint--;
            if (toBePrint == 0) {
                res.add(levelList);
                levelList = new LinkedList<Integer>();
                toBePrint = nextLevel;
                nextLevel = 0;
                leftToRight = !leftToRight;
            }
        }
        return res;
    }
}
