package LeetCode;

/**
 * 题目: 61.旋转链表
 * 思路: 先计算链表长度,然后把k%len个节点移到前面.
 */
public class Problem61 {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }

    private ListNode moveToHead(ListNode head, int k) {
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode before = head;
        ListNode after = head;
        while (k > 0) {
            after = after.next;
            k--;
        }
        while (after.next != null) {
            after = after.next;
            before = before.next;
        }
        ListNode cur = before.next;
        before.next = null;
        after.next = newHead.next;
        newHead.next = cur;
        return newHead.next;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        // 计算链表长度
        int listLen = 0;
        ListNode p = head;
        while (p != null) {
            p = p.next;
            listLen++;
        }
        //将尾部k%len个节点移到前面即可
        k = k % listLen;
        if (k == 0) {
            return head;
        }
        head = moveToHead(head, k);
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        Problem61 p = new Problem61();
        p.rotateRight(node1, 2);
    }
}
