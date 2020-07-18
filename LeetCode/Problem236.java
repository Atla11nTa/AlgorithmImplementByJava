package LeetCode;

/**
 * 题目: 236. 二叉树的最近公共祖先
 * 地址: https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * 思路: 后序遍历. 用一个全局变量保存结果, 总结左右子树的结果. 找到一个答案就返回true.
 */
public class Problem236 {
     static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;

         TreeNode(int x) {
             val = x;
         }
     }

    private TreeNode ans = null;

    private boolean traverse(TreeNode root, TreeNode p, TreeNode q) {
        // 已经找到答案也提前退出
        if (root == null || ans != null) {
            return false;
        }
        boolean lNode = traverse(root.left, p, q);
        boolean rNode = traverse(root.right, p, q);
        // 左右都有或者左右有其一,自己是另一. 那么就找到答案了
        if ((lNode && rNode) || ((root == p || root == q) && (lNode || rNode))) {
            ans = root;
        }
        // 找到一个答案返回true.
        return lNode || rNode || (root == p || root == q);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.traverse(root, p, q);
        return this.ans;
    }
}
