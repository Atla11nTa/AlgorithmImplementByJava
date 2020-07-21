package LeetCode.Tree;

/**
 * 题目: 二叉搜索树的最近公共祖先
 * 思路: 利用二叉搜索树的特点, 若p,q都小于root的值,就在左边找,若p,q都大于root的值,就在右边找,否则root就是答案
 */
public class Problem235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int parentVal = root.val;
        int pVal = p.val;
        int qVal = q.val;
        if (pVal > parentVal && qVal > parentVal) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root;
        }
    }
}
