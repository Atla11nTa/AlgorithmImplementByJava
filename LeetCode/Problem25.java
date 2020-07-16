package LeetCode;

/**
 * 题目: 25. K个一组旋转链表
 * 思路: 每k个节点进行一次翻转, 每k个为一个翻转节. begin是翻转节的头节点, end为翻转节的尾节点. 那么翻转后, begin将变为尾,end变为头.
 * 而且还要将翻转后的连接到之前的链表上, 所以要记录翻转节的前一个节点preBegin, 和后一个节点endNext.
 * 翻转后, preBegin.next = end, begin.next = endNext.
 */
public class Problem25 {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    /**
     * preBegin: 翻转节头节点的前一个节点
     * endNext: 翻转节尾节点的下一个节点
     * begin: 翻转节的头
     * end: 翻转节的尾
     */
    public static void reverseList(ListNode preBegin, ListNode end) {
        ListNode begin = preBegin.next;
        ListNode endNext = end.next;

        ListNode pre = begin;
        ListNode next = pre.next;
        while (next != endNext) {
            ListNode temp = next.next;
            next.next = pre;
            pre = next;
            next = temp;
        }
        begin.next = endNext;
        preBegin.next = end;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k == 0) {
            return head;
        }
        ListNode trueHead = new ListNode(-1);
        trueHead.next = head;

        ListNode cur = head;
        ListNode preBegin = trueHead;
        int count;
        outer:
        while (cur != null) {
            count = 1;
            while (count < k) {
                cur = cur.next;
                //不足k个
                if (cur == null) {
                    break outer;
                }
                count++;
            }
            ListNode begin = preBegin.next;
            //把preBegin和cur之间的链表反转
            reverseList(preBegin, cur);
            // 完成翻转后, 上一个翻转节的头变为preBegin.
            preBegin = begin;
            cur = preBegin.next;
        }

        return trueHead.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = null;
        ListNode head = node1;
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println();
        head = reverseKGroup(node1, 4);
        while (head != null) {

            System.out.print(head.val + "->");
            head = head.next;
        }
    }
}
