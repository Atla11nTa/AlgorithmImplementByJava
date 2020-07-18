package LeetCode;

import java.util.List;

/**
 * 题目: 21.合并两个有序链表
 * 思路: 先从两个链表的头找一个较小的作为头节点,然后依次的将后续的较小值插入到之后.
 */
public class Problem21 {
     static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) {
              this.val = val;
          }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
     public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
         if (l1 == null) {
             return l2;
         } else if (l2 == null) {
             return l1;
         }
         ListNode p1, p2;
         ListNode newHead;
         if (l1.val > l2.val) {
             newHead = l2;
             p1 = l2.next;
             p2 = l1;
         } else {
             newHead = l1;
             p1 = l1.next;
             p2 = l2;
         }
         ListNode newLast = newHead;
         while (p1 != null && p2 != null) {
             if (p1.val < p2.val) {
                 newLast.next = p1;
                 newLast = newLast.next;
                 p1 = p1.next;
             } else {
                 newLast.next = p2;
                 newLast = newLast.next;
                 p2 = p2.next;
             }
         }
         if (p1 != null) {
             newLast.next = p1;
         }
         if (p2 != null) {
             newLast.next = p2;
         }
         return newHead;
     }
}
