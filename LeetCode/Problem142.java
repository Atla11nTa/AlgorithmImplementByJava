package LeetCode;

/**
 * 题目: 142.环形链表II
 */
public class Problem142 {
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == head) {
            return head;
        }
        // 用一个快慢指针找是否有环
        ListNode fast = head;
        ListNode slow = head;
        boolean flag = true;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                flag = false;
                break;
            }
        }
        // 说明无环
        if (flag) {
            return null;
        }
        // 求环的长度
        slow = slow.next;
        int count = 1;
        while (slow != fast) {
            slow = slow.next;
            count++;
        }

        // 求入环节点, fast先走count步,然后一起走,最终在入环处相遇
        slow = head;
        fast = head;
        while (count > 0) {
            fast = fast.next;
            count--;
        }
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(4);
        ListNode h1 = new ListNode(2);
        ListNode h2 = new ListNode(1);
        ListNode h3 = new ListNode(3);
        l1.next = h1;
        h1.next = h2;
        h2.next = h3;
        h3.next = h1;
        System.out.print(detectCycle(l1).val);

    }
}
