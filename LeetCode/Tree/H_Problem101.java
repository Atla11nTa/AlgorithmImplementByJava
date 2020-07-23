package LeetCode.Tree;

import java.util.*;

/**
 * 题目: 101.对称二叉树
 * 思路1: 迭代法.每次取出两个节点比较是否相同, 之后应该是其一的左子树比较右子树, 右子树比较左子树,所以是交替入队列. 注意的是开始根入队列两次.
 * 缺点:相当多的重复计算,效率低
 * 思路2: 递归法. 一个树是否为对称二叉树 = 两个根节点值相同, 且右子树与左子树镜像对称.
 */
public class H_Problem101 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);
        TreeNode left;
        TreeNode right;
        while (!queue.isEmpty()) {
            left = queue.poll();
            right = queue.poll();
            if (left == null && right == null) {
                continue;
            }
            if ((left == null || right == null) || left.val != right.val) {
                return false;
            }
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }
    public boolean isSymmetric2(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        // 第一个check,p向左走,q向右走,比较p的左子树是否等于q的右子树. 第二个check,p向右走,q向左走.比较p的右子树是否等于q的左子树.
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }
}
