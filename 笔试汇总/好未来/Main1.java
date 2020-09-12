package 笔试汇总.好未来;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Main1 {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.val = data;
        }
    }
    /**
     * 非递归遍历前序二叉树
     * @param root TreeNode类 根节点
     * @return string字符串
     */
    public String notReCuPreOrder (TreeNode root) {
        // write code here
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.append(cur.val).append(",");
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return res.substring(0, res.length() - 1);
    }
}
