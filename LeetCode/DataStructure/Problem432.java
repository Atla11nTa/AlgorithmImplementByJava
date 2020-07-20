package LeetCode.DataStructure;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.util.HashMap;

/**
 * 题目： 432.全O(1)的数据结构
 * 缺陷：还不是真正的O(1)，待改进。
 * 数据结构设计：哈希表和双向链表
 * 哈希表实现快速查找，双向链表实现快速删除，组成一个有序的双向链表，频度最低的在第一个，最高的在最后。
 * inc：每次inc，从尾部找比node频度低的节点，在后面插入。
 * dec: 每次dec，从头部找比node频度高，在前面插入。
 */

public class Problem432 {
    HashMap<String, Node> hashMap;
    Node head;
    Node tail;

    public Problem432() {
        head = new Node("", -1);
        tail = new Node("", Integer.MAX_VALUE);
        head.next = tail;
        tail.pre = head;
        hashMap = new HashMap<>();
    }

    public void inc(String key) {
        Node node = hashMap.get(key);
        //若存在，则加一
        if (node != null) {
            node.val++;
            hashMap.put(key, node);
            node.pre.next = node.next;
            node.next.pre = node.pre;
            //从尾找到比node频度小的节点，在之后插入。
            Node p = tail;
            while (p.val > node.val) {
                p = p.pre;
            }
            Node next = p.next;
            node.next = next;
            node.pre = p;
            p.next = node;
            next.pre = node;
        }
        //若不存在，就在head后面插入
        else {
            node = new Node(key, 1);
            hashMap.put(key, node);
            Node headNext = head.next;
            node.pre = head;
            node.next = headNext;
            headNext.pre = node;
            head.next = node;
        }
    }

    public void dec(String key) {
        Node node = hashMap.get(key);
        //为null不做处理
        if (node != null) {
            // 频度为1删除
            if (node.val == 1) {
                hashMap.remove(key);
                Node pre = node.pre;
                Node next = node.next;
                pre.next = next;
                next.pre = pre;
            } else {
                node.val--;
                hashMap.put(key, node);
                node.pre.next = node.next;
                node.next.pre = node.pre;
                //从头找到比node频度大的节点p，在之前插入。
                Node p = head;
                while (p.val < node.val) {
                    p = p.next;
                }
                Node pre = p.pre;
                node.pre = pre;
                node.next = p;
                pre.next = node;
                p.pre = node;
            }
        }
    }

    public String getMaxKey() {
        return tail.pre.key;
    }

    public String getMinKey() {
        return head.next.key;
    }
    static class Node{
        Node pre;
        Node next;
        String key;
        int val;

        Node(String key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
