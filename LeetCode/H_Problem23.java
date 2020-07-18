package LeetCode;

import java.util.List;
import java.util.PriorityQueue;

/**
 * 题目: 23.合并k个有序链表
 * 思路1: 两两顺序合并, 时间复杂度为O(k^2*N)
 * 思路2: 归并排序, 两两合并, 时间复杂度O(klogk*N)
 * 思路3: 使用优先队列. 把k个链表的头节点存入堆中,然后每次取出值最小的节点, 然后再将其next加入堆中
 * 若是合并K个有序数组,那么思路3最简单且容易实现.
 */

public class H_Problem23 {
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    private ListNode mergeTwoList(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode(-1);
        ListNode cur = newHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return newHead.next;
    }
    // 思路1: 顺序合并
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode head = lists[0];
        for (int i = 1; i < lists.length; i++) {
            head = mergeTwoList(head, lists[i]);
        }
        return head;
    }

    // 思路2: 归并合并
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = (left + right) / 2;
        ListNode leftHead = merge(lists, left, mid);
        ListNode rightHead = merge(lists, mid + 1, right);
        return mergeTwoList(leftHead, rightHead);
    }

    // 思路3: 使用优先队列合并
    public ListNode mergeKLists3(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        // 按节点的val比较大小建小根堆.
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a,b)->{
            return a.val - b.val;
        });
        for (var list : lists) {
            if (list != null) {
                queue.offer(list);
            }
        }
        ListNode newHead = new ListNode(-1);
        ListNode cur = newHead;
        ListNode next;
        // 每次从堆中取出一个值最小的节点,并将其next加入堆中.
        while (!queue.isEmpty()) {
            cur.next = queue.peek();
            cur = cur.next;
            next = queue.poll().next;
            if (next != null) {
                queue.offer(next);
            }
        }
        return newHead.next;
    }

}
