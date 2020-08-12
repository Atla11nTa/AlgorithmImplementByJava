package LeetCode.Tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 题目：99. 恢复二叉搜索树
 * 题目描述： 二叉搜索树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 * 思路： 由于一次交换，会导致中序序列出现两次或者一次乱序，所以找到乱序位置，交换即可
 */
public class Problem99 {
    public void recoverTree(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = null;
        // x记录第一个乱序点，y记录第二个乱序点。
        TreeNode x = null, y = null;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (pre != null && root.val < pre.val) {
                    y = root;
                    if (x == null) {
                        x = pre;
                    } else {
                        break;
                    }
                }
                pre = root;
                root = root.right;
            }
        }
        int temp = x.val;
        x.val = y.val;
        y.val = x.val;
    }
}
