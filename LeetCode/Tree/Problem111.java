package LeetCode.Tree;

/**
 * 题目：二叉树的最小深度
 */
public class Problem111 {
    int res = Integer.MAX_VALUE;

    public void midDepth(TreeNode root, int deep) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            res = Math.min(res, deep);
        }
        midDepth(root.left, deep + 1);
        midDepth(root.right, deep + 1);
    }
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        midDepth(root, 1);
        return res;
    }
}
