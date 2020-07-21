package LeetCode.Tree;

import java.util.*;

/**
 * 863. 二叉树中所有距离为 K 的结点
 * 思路：先建立一个Map保存每个节点的父节点，这样就相当于建立了一个图，然后以target为起点找距离为K的节点即可。
 * 找距离为K的节点可利用深度或广度优先遍历。
 * 广度优先遍历： toVisit: 保存本层剩余打印的节点数。toVisit为0时，一层打印完，路径长度+1。
 *              nextLevel：保存下一层的节点数，
 */
 class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}
public class Problem863 {
    // 保存每个节点的父节点。
    Map<TreeNode, TreeNode> parentMap = new HashMap<>();


    private void traverse(TreeNode node, TreeNode parent) {
        if (node != null) {
            parentMap.put(node, parent);
            traverse(node.left, node);
            traverse(node.right, node);
        }
    }

    private void Bfs(TreeNode root, List<Integer> res, int pathLen, int K, Set<TreeNode> isVisited) {
        if (pathLen == K) {
            res.add(root.val);
            return;
        } else {
            if (root.left != null && !isVisited.contains(root.left)) {
                isVisited.add(root.left);
                Bfs(root.left, res, pathLen + 1, K, isVisited);
            }
            if (root.right != null && !isVisited.contains(root.right)) {
                isVisited.add(root.right);
                Bfs(root.right, res, pathLen + 1, K, isVisited);
            }
            TreeNode parent = parentMap.get(root);
            if (parent != null && !isVisited.contains(parent)) {
                isVisited.add(parent);
                Bfs(parent, res, pathLen + 1, K, isVisited);
            }
        }
    }

    public List<Integer> distanceK2(TreeNode root, TreeNode target, int K) {
        List<Integer> resList = new ArrayList<>();
        if (K == 0) {
            resList.add(target.val);
            return resList;
        }
        //先构造父节点。
        traverse(root, null);
        Set<TreeNode> isVisited = new HashSet<>();
        isVisited.add(target);
        Bfs(target, resList, 0, K, isVisited);
        return resList;
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> resList = new ArrayList<>();
        if (K == 0) {
            resList.add(target.val);
            return resList;
        }
        //先构造父节点。
        traverse(root, null);
        Set<TreeNode> isVisited = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        isVisited.add(target);
        int pathLen = 0;
        int toVisit = 1;
        int nextLevel = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null && !isVisited.contains(cur.left)) {
                nextLevel++;
                isVisited.add(cur.left);
                queue.offer(cur.left);
                if (pathLen == K - 1) {
                    resList.add(cur.left.val);
                }
            }
            if (cur.right != null && !isVisited.contains(cur.right)) {
                nextLevel++;
                isVisited.add(cur.right);
                queue.offer(cur.right);
                if (pathLen == K - 1) {
                    resList.add(cur.right.val);
                }
            }
            TreeNode parent = parentMap.get(cur);
            if (parent != null && !isVisited.contains(parent)) {
                nextLevel++;
                isVisited.add(parent);
                queue.offer(parent);
                if (pathLen == K - 1) {
                    resList.add(parent.val);
                }
            }
            toVisit--;
            //一层访问完毕
            if (toVisit == 0) {
                pathLen++;
                toVisit = nextLevel;
                nextLevel = 0;
            }
            if (pathLen == K) {
                break;
            }
        }
        return resList;
    }

}

