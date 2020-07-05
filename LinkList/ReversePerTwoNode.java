package LinkList;

/**
 * 问题描述：链表每两个节点翻转一次，不足两个则不操作
 * 解决方法：
 * 1. 首先注意判断节点数小于2应该直接返回
 * 2. 其次，结果的头节点应该是最初head的next，因为之后会做翻转
 * 3. 添加一个空节点pre指向head, p/q分别执行待操作的节点,last指向q.next。循环更新，注意当last或者last.next为空时退出循环
 */
public class ReversePerTwoNode {
    public static Node<Integer> solution(Node<Integer> head){
        if(head == null || head.next == null){
            return head;
        }else {
            Node<Integer> p = head;
            Node<Integer> q = head.next;
            Node<Integer> pre = new Node<>(0,head);
            Node<Integer> last = q.next;
            head = q;
            while(true){
                p.next = last;
                pre.next = q;
                q.next = p;
                pre = p;
                if(last == null || last.next == null){
                    break;
                }else {
                    p = last;
                    q = last.next;
                    last = q.next;
                }
            }
            return head;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {1,2,3};
        Node<Integer> head = List.InitList(arr);
        System.out.println(head);
        head = solution(head);
        System.out.println(head);
    }
}
