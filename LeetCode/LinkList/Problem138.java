package LeetCode.LinkList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目: 138. 复制带随机指针的链表
 * 思路: O(N)空间复杂度的解法: 用哈希表存每个节点的索引.
 * 思路2: O(1)空间复杂度的解法: 先复制一边节点, 比如: A->B->C->null变为: A->A'->B->B'->C->C'->null
 * 然后第二次遍历复制random指针, B'.random = B.random.next.
 * 第三次遍历就将复制节点从原来的链表上摘除下来.
 */

public class Problem138 {
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        List<Node> list = new ArrayList<>();
        Map<Node, Integer> indexMap = new HashMap<>();
        Node p = head;
        Node cur = new Node(p.val);
        indexMap.put(p, 0);
        list.add(cur);
        p = p.next;
        Node pre = cur;

        int index = 1;
        while (p != null) {
            indexMap.put(p, index++);
            cur = new Node(p.val);
            pre.next = cur;
            list.add(cur);
            pre = cur;
            p = p.next;
        }
        p = head;
        index = 0;
        while (p != null) {
            if (p.random != null) {
                list.get(index).random = list.get(indexMap.get(p.random));
            }
            index++;
            p = p.next;
        }
        return list.get(0);
    }

    public static void main(String[] args) {
        Node node1 = new Node(0);
        Node node2 = new Node(1);
        Node node3 = new Node(2);
        node1.next = node2;
        node2.next = node3;
        node2.random = node1;
        copyRandomList(node1);
    }
}
