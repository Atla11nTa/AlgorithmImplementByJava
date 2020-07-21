package LeetCode.Tree;

/**
 * 题目：124.二叉树中的最大路径和
 * 注意：这个最大路径和表示从任意一个节点到另一个节点的和，不能经过同一个节点多次，注意这点。
 * 最大路径和： 以root为根的最大路径和 = 从左分支走到root再走向右分支的路径和，如果左分支或者右分支的路径小于0，自然不选择。
 * 将本层的最佳结果返回上层，最佳结果就root+max(left,right)， 因为前面处理过，left,right最小就是0.
 */
public class Problem124 {
    private int max = Integer.MIN_VALUE;
    private int traverse(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 左分支的最大值，小于0就不选择左分支
        int left = Math.max(traverse(root.left), 0);
        // 右分支的最大值，小于0就不选择
        int right = Math.max(traverse(root.right),0);
        // 从左分支经根结点走向右分支的最大路径和
        int weight = root.val + left + right;
        max = Math.max(weight, max);
        // 选择一个最大的分支与根结点的和作为返回值，
        return root.val + Math.max(left, right);
    }

    public int maxPathSum(TreeNode root) {
        traverse(root);
        return max;
    }
}
