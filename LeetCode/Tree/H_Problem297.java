package LeetCode.Tree;

import java.util.*;

/**
 * 题目： 297. 二叉树的序列化与反序列化
 * 思路： 利用先序遍历序列化和反序列化。 若是使用层次遍历序列化，那么就也要用层次遍历反序列化。
 */
public class H_Problem297 {
    private void traverse(List<String> list, TreeNode root) {
        if (root != null) {
            list.add(String.valueOf(root.val));
            if (root.left == null) {
                list.add("null");
            } else {
                traverse(list, root.left);
            }
            if (root.right == null) {
                list.add("null");
            } else {
                traverse(list, root.right);
            }
        }
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> list = new ArrayList<>();
        traverse(list, root);
        return String.join(",", list);
    }


    private TreeNode decode(List<String> list) {
        if (list.get(0).equals("null")) {
            list.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        root.left = decode(list);
        root.right = decode(list);
        return root;
    }

    public TreeNode deserialize(String data) {
        if (data.equals("")) {
            return null;
        }
        List<String> list = new LinkedList<>(Arrays.asList(data.split(",")));
        TreeNode root = decode(list);
        return root;
    }

    public static void main(String[] args) {
        H_Problem297 p = new H_Problem297();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        System.out.println(p.serialize(node1));

    }
}
