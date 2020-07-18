package LeetCode;

/**
 * 题目: 160.相交链表
 *
 */
public class Problem160 {
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 求链表1的长度
        int list1Len = 0;
        ListNode p = headA;
        while (p != null) {
            p = p.next;
            list1Len++;
        }
        //求链表2的长度
        int list2Len = 0;
        p = headB;
        while (p != null) {
            p = p.next;
            list2Len++;
        }
        ListNode longest;
        ListNode shortest;
        longest = list1Len > list2Len ? headA : headB;
        shortest = list1Len > list2Len ? headB : headA;
        // 较长的先走
        int cut = Math.abs(list1Len - list2Len);
        while (cut > 0) {
            longest = longest.next;
            cut--;
        }
        boolean flag = true;
        while (longest != null && shortest != null) {
            if (longest == shortest) {
                flag = false;
                break;
            }
            longest = longest.next;
            shortest = shortest.next;
        }
        //没相交
        if (flag) {
            return null;
        }
        return longest;
    }
}
