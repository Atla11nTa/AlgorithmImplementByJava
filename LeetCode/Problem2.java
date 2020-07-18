package LeetCode;

/**
 * 题目: 2.两数相加
 * 地址: https://leetcode-cn.com/problems/add-two-numbers/
 * 思路: 注意进位问题.
 */
public class Problem2 {
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode pre = new ListNode(-1);
        pre.next = p1;
        int sum = 0;
        int flag = 0;
        while (p1 != null && p2 != null) {
            sum = p1.val + p2.val + flag;
            if (sum >= 10) {
                p1.val = sum % 10;
                flag = 1;
            } else {
                p1.val = sum;
                flag = 0;
            }
            p2 = p2.next;
            pre = p1;
            p1 = p1.next;
        }
        while (p1 != null) {
            sum = p1.val + flag;
            if (sum >= 10) {
                p1.val = sum % 10;
                flag = 1;
            } else {
                p1.val = sum;
                flag = 0;
            }
            pre = p1;
            p1 = p1.next;
        }
        if (p2 != null) {
            pre.next = p2;
            p1 = p2;
            while (p1 != null) {
                sum = p1.val + flag;
                if (sum >= 10) {
                    p1.val = sum % 10;
                    flag = 1;
                } else {
                    p1.val = sum;
                    flag = 0;
                }
                pre = p1;
                p1 = p1.next;
            }
        }
        if (flag == 1) {
            pre.next = new ListNode(1);
        }
        return l1;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode h1 = new ListNode(9);
        ListNode h2 = new ListNode(9);
        h1.next = h2;
        addTwoNumbers(l1, h1);
    }
}
