package 笔试汇总.Shopee;

import java.util.ArrayDeque;
import java.util.Deque;

class ListNode {
    int val;
    ListNode next;
    ListNode() {
    }
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
public class Main3 {

    public static ListNode reverseList (ListNode head) {
        // write code here
        Deque<ListNode> stack = new ArrayDeque<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        head = stack.pop();
        ListNode cur = head;
        ListNode next = null;
        while (!stack.isEmpty()) {
            next = stack.pop();
            cur.next = next;
            cur = cur.next;
        }
        cur.next = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        reverseList(node1);

    }
}
