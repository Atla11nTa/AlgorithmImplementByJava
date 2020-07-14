package Tree;

import javax.print.DocFlavor;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目: 按行打印二叉树的节点
 * toBePrint保存本层剩余打印数
 * nextLevel保存下一层的节点.
 * 这个解法还可以用于计算树的最大宽度.
 */

public class RowOrderTraverse {
    public static void solution(TreeNode<Integer> head) {
        if (head == null) {
            return;
        }
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(head);
        int toBePrint = 1;
        int nextLevelCount = 0;
        TreeNode<Integer> curNode = null;
        while (!queue.isEmpty()) {
            curNode = queue.poll();
            System.out.print(curNode.data + '\t');
            if (curNode.left != null) {
                queue.offer(curNode.left);
                nextLevelCount++;
            }
            if (curNode.right != null) {
                queue.offer(curNode.right);
                nextLevelCount++;
            }
            toBePrint --;
            if (toBePrint == 0) {
                System.out.print('\n');
                toBePrint = nextLevelCount;
                nextLevelCount = 0;
            }
        }
    }
}
