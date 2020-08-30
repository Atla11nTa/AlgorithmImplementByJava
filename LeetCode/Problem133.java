package LeetCode;

import java.util.*;

/**
 * 133. 克隆图
 * 使用广度优先遍历克隆，注意使用HashMap，建议对应关系HashMap<node,cloneNode>
 */
public class Problem133 {
    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public static Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(node);
        Node newNode = new Node(node.val);
        HashMap<Node, Node> hashMap = new HashMap<>();
        hashMap.put(node, newNode);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (var next : cur.neighbors) {
                if (!hashMap.containsKey(next)) {
                    hashMap.put(next, new Node(next.val));
                    queue.offer(next);
                }
                hashMap.get(cur).neighbors.add(hashMap.get(next));
            }
        }
        return newNode;
    }
}
