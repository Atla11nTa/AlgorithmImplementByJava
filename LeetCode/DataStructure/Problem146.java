package LeetCode.DataStructure;

import java.util.HashMap;

/**
 * 题目： 146.LRU缓存
 * 特点：当容量达到上限时，删除最近最久未访问(访问包括读取和更新)的数据
 * 数据结构设计： 双向链表和哈希表
 * 技巧：1. 为了方便链表操作，使用两个空的头尾节点，每次新元素插在tail前，删除就删除head的下一个。头尾永远不变。
 */

public class Problem146 {
    private HashMap<Integer, Node> hashMap;
    private Node head;
    private Node tail;
    private int size;
    private int capacity;

    public Problem146(int capacity) {
        this.hashMap = new HashMap<>(capacity);
        this.size = 0;
        this.capacity = capacity;
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.pre = null;
        head.next = tail;
        tail.pre = head;
        tail.next = null;
    }

    public int get(int key) {
        Node node = hashMap.get(key);
        if (node == null) {
            return -1;
        } else {
            Node pre = node.pre;
            Node next = node.next;
            pre.next = next;
            next.pre = pre;
            //移到链表尾部
            nodeMoveToTail(node);
            return node.value;
        }
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        //如果已经有了，就移到尾部，记得更新哈希表中的值
        Node node = hashMap.get(key);
        if (node != null) {
            node.value = value;
            hashMap.put(key, node);
            Node pre = node.pre;
            Node next = node.next;
            pre.next = next;
            next.pre = pre;
            //移到链表尾部
            nodeMoveToTail(node);
        } else {
            // 容量达到上限.
            if (size == capacity) {
                // 把原来的头部第一个节点删除
                hashMap.remove(head.next.key);
                Node headNext = head.next.next;
                head.next = headNext;
                headNext.pre = head;
                size--;
            }
            size++;
            //在尾部插入
            Node newNode = new Node(key, value);
            nodeMoveToTail(newNode);
            hashMap.put(key, newNode);
        }
    }

    //把节点移到尾部
    private void nodeMoveToTail(Node node) {
        Node pre = tail.pre;
        node.next = tail;
        node.pre = pre;
        pre.next = node;
        tail.pre = node;
    }
    static class Node {
        Node pre;
        Node next;
        int key;
        int value;

        Node(int key,int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Problem146 cache = new Problem146( 1 /* 缓存容量 */ );
        cache.put(2, 1);
        cache.get(2);       // 返回  1
        cache.put(3, 2);
        cache.get(2);
        cache.get(3);
    }
}
