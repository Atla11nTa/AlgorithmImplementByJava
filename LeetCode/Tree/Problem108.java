package LeetCode.Tree;

/**
 * 题目: 108.将有序数组转为二叉搜索树
 * 思路: 每次选择下中位数作为根,注意是下中位数
 */

public class Problem108 {
    private TreeNode buildTree(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        if (left == right) {
            TreeNode node = new TreeNode(nums[left]);
            return node;
        }
        int mid;
        if ((right - left) % 2 == 0) {
            mid = left + (right - left) / 2 + 1;
        } else {
            mid = left+(right-left)/2;
        }
        TreeNode leftNode = buildTree(nums, left, mid - 1);
        TreeNode rightNode = buildTree(nums, mid + 1, right);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }
}
