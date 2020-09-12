package LeetCode.LinkList;

public class Problem {
    public static ListNode solution(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode preNode = newHead;
        ListNode p = preNode.next;
        ListNode nextNode = p.next.next;
        while (true) {
            preNode.next = p.next;
            p.next = nextNode;
            preNode.next.next = p;
            preNode = p;
            p = preNode.next;
            if (p == null || p.next == null) {
                break;
            }
            nextNode = p.next.next;
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;

        ListNode head = solution(node1);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}
