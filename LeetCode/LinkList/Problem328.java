package LeetCode.LinkList;

/**
 * 题目： 328.奇偶链表
 * 思路：两个链表
 */

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
public class Problem328 {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        // 奇链表头
        ListNode oddHead = new ListNode(-1);
        ListNode oddLast = oddHead;
        // 偶链表头
        ListNode evenHead = new ListNode(-1);
        ListNode evenLast = evenHead;

        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode cur = head;
        boolean isOdd = true;
        while (cur != null) {
            if (isOdd) {
                newHead.next = cur.next;
                oddLast.next = cur;
                oddLast = cur;
            } else {
                newHead.next = cur.next;
                evenLast.next = cur;
                evenLast = cur;
            }
            cur.next = null;
            isOdd = !isOdd;
            cur = newHead.next;
        }
        oddLast.next = evenHead.next;
        return oddHead.next;
    }
}
