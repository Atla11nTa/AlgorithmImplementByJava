package LeetCode.Tree;

/**
 * 230. 二叉搜索树中第K小的元素
 * 思路：
 * 二叉搜索树中序遍历是升序序列，所以第k个访问的节点就是结果。
 * 如果得到全部的中序遍历序列也可以，但是时间空间复杂度更高。
 */
public class Problem230 {
    private Integer res;
    private int k;
    private void traverse(TreeNode root) {
        if (root != null && res == null) {
            traverse(root.left);
            this.k--;
            if (k == 0) {
                res = root.val;
            }
            traverse(root.right);
        }
    }
    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        traverse(root);
        return res;
    }
}
