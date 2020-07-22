package LeetCode.Tree;

/**
 * 题目: 110.判断一个二叉树是否是高度平衡的平衡二叉树
 * 思路: 求树高度.
 */
public class Problem110 {
    private boolean res = true;
    private int traverse(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDeep = traverse(root.left);
        int rightDeep = traverse(root.right);
        if (Math.abs(leftDeep - rightDeep) > 1) {
            res = false;
        }
        return Math.max(leftDeep, rightDeep) + 1;
    }

    public boolean isBalanced(TreeNode root) {
        int deep = traverse(root);
        return res;
    }
}
