package LeetCode.Tree;

/**
 * 题目: 105. 从前序与中序遍历序列构造二叉树
 *
 */
public class Problem105 {
    private TreeNode build(int[] preorder, int left1, int right1, int[] inorder, int left2, int right2) {
        if (left1 > right1 || left2 > right2) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[left1]);
        int rootVal = root.val;
        int rootIndex = -1;
        for (int i = left2; i <= right2; i++) {
            if (inorder[i] == rootVal) {
                rootIndex = i;
            }
        }
        int leftTreeLen = rootIndex - left2;
        root.left = build(preorder, left1 + 1, left1 + leftTreeLen, inorder, left2, rootIndex - 1);
        root.right = build(preorder, left1 + leftTreeLen + 1, right1, inorder, rootIndex + 1, right2);
        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
}
