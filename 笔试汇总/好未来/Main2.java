package 笔试汇总.好未来;

import java.util.List;

public class Main2 {
    static class ListNode{
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
        }
    }
    public ListNode reverseList (ListNode head) {
        // write code here
        ListNode newHead = new ListNode(-1);
        ListNode next;
        ListNode p = head;
        while (p != null) {
            next = p.next;
            p.next = newHead.next;
            newHead.next = p;
            p = next;
        }
        return newHead.next;
    }
}
