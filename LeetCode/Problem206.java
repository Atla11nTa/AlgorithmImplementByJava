package LeetCode;

/**
 * 题目: 206.翻转链表
 */

public class Problem206 {
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = new ListNode(-1);
        ListNode p = head;
        ListNode next = p.next;
        while (true) {
            p.next = newHead.next;
            newHead.next = p;
            p = next;
            if (p == null) {
                break;
            }
            next = p.next;
        }
        return newHead.next;
    }
}
