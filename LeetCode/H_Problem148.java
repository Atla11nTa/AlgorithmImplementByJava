package LeetCode;

/**
 * 题目: 148.排序链表
 * https://leetcode-cn.com/problems/sort-list/
 * 要求:时间复杂度O(nlogn),空间复杂度O(1)
 * 思路: 因为有空间复杂度要求,也就是不能申请数组保存. 所以只能考虑到归并, 因为是归并排序,还涉及到找链表的中间节点,所以综合性还可以.
 */
public class H_Problem148 {
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    private static ListNode midNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 注意,二者不能相同起点.
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private static ListNode mergeTwoList(ListNode l1, ListNode l2) {
        ListNode newhead = new ListNode(-1);
        ListNode p = newhead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        p.next = l1 != null ? l1 : l2;
        return newhead.next;
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 找到中间节点,然后将中间节点断开,分为左右两个链表
        ListNode mid = midNode(head);
        ListNode rightHead = mid.next;
        mid.next = null;
        //递归左部分
        ListNode left = sortList(head);
        //递归右部分
        ListNode right = sortList(rightHead);

        //合并左右有序部分
        return mergeTwoList(left, right);
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(4);
        ListNode h1 = new ListNode(2);
        ListNode h2 = new ListNode(1);
        ListNode h3 = new ListNode(3);
        l1.next = h1;
        h1.next = h2;
        h2.next = h3;
        sortList(l1);
    }
}
