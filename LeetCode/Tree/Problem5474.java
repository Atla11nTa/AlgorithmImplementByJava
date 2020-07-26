package LeetCode.Tree;

import java.util.*;

/**
 * 题目: 5474. 好叶子节点对的数量
 * 地址:https://leetcode-cn.com/problems/number-of-good-leaf-nodes-pairs/
 * 思路1: 先构建一个hashMap存储到每个节点到父节点的映射, 把树转为图, 然后从每个叶子节点开始执行BFS
 *思路2: 通过一个后序遍历, 每次遍历返回一个map, 里面保存了自己下面长度为key的叶子节点的个数. 整合左右子树的结果, 然后作为自己的返回即可.
 */
public class Problem5474 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {
        }
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 存储节点到父节点的映射
    Map<TreeNode, TreeNode> parentMap = new HashMap<>();
    // 存储叶子节点
    List<TreeNode> nodeList = new ArrayList<>();
    // 存储已经找过答案的叶子节点
    Set<TreeNode> resSet = new HashSet<>();

    // 通过先序遍历构建映射
    private void traverse(TreeNode root, TreeNode parent) {
        if (root == null) {
            return;
        }
        //叶子节点
        if (root.left == null && root.right == null) {
            nodeList.add(root);
        }
        parentMap.put(root, parent);
        traverse(root.left, root);
        traverse(root.right, root);
    }

    // 广度优先遍历找符合的结果
    private int bfs(TreeNode root,int distance) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode cur;
        TreeNode parent;
        int nextLevel = 0;
        int toBeVisit = 1;
        resSet.add(root);
        int res = 0;
        int pathLen = 0;
        Set<TreeNode> isVisited = new HashSet<>();
        isVisited.add(root);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur.left != null && !isVisited.contains(cur.left)) {
                queue.offer(cur.left);
                isVisited.add(cur.left);
                nextLevel++;
            }
            if (cur.right != null && !isVisited.contains(cur.right)) {
                isVisited.add(cur.right);
                queue.offer(cur.right);
                nextLevel++;
            }
            parent = parentMap.get(cur);
            if (parent != null && !isVisited.contains(parent)) {
                isVisited.add(parent);
                queue.offer(parent);
                nextLevel++;
            }
            // 是叶子节点
            if (cur.left == null && cur.right == null && !resSet.contains(cur) && pathLen <= distance) {
                res++;
            }
            toBeVisit--;
            if (toBeVisit == 0) {
                toBeVisit = nextLevel;
                nextLevel = 0;
                pathLen++;
            }
        }
        return res;
    }
    public int countPairs(TreeNode root, int distance) {
        traverse(root, null);
        int res = 0;
        for (int i = 0; i < nodeList.size() - 1; i++) {
            res += bfs(nodeList.get(i), distance);
        }
        return res;
    }
}
