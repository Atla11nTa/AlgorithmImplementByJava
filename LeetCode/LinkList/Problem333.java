package LeetCode.LinkList;

public class Problem333 {
    public ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newHead = new ListNode(-1);
        ListNode pre = head;
        newHead.next = head;
        ListNode cur = head.next;
        ListNode next;
        while (cur != head) {
            next = cur.next;
            cur.next = pre;
            newHead.next = cur;
            pre = cur;
            cur = next;
        }
        cur.next = newHead.next;
        return newHead.next;
    }

    public static void main(String[] args) {
        Problem333 p = new Problem333();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node1;
        p.reverse(node1);
    }
}
