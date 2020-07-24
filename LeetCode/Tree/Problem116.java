package LeetCode.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class Problem116 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int toBeVisit = 1;
        int nextLevel = 0;
        Node pre = null;
        Node cur;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur.left != null) {
                queue.offer(cur.left);
                nextLevel++;
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                nextLevel++;
            }
            toBeVisit--;
            if (toBeVisit == 0) {
                if (pre != null) {
                    pre.next = cur;
                }
                cur.next = null;
                pre = null;
                toBeVisit = nextLevel;
                nextLevel = 0;
            } else {
                if (pre != null) {
                    pre.next = cur;
                }
                pre = cur;
            }
        }
        return root;
    }

    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            root.left.next = root.right;
        }
        // 利用父节点的next指针构建右孩子的next
        if (root.right != null) {
            root.right.next = root.next != null ? root.next.left : null;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }
}
