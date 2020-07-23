package LeetCode.Tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 题目: 98.判断一个二叉树是否为二叉搜索树
 * 思路: 非递归判断中序遍历的当前节点是否大于前一个节点.
 */
public class Problem98 {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        double pre = -Double.MAX_VALUE;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= pre) {
                return false;
            }
            pre = root.val;
            root = root.right;
        }
        return true;
    }
}
