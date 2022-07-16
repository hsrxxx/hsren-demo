package com.hsren.algorithm;

import com.hsren.dataStructure.ListNode;

/**
 * 反转链表
 */
public class ReverseListNode {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5)))));
        ReverseList(l1).print();
    }

    public static ListNode ReverseList(ListNode head) {
        ListNode next = null;
        ListNode pre = null;
        while (head != null) {
            // 保存下一个节点
            next = head.next;
            // 垫脚石，保存当前反转链表 （第一次为null）
            head.next = pre;
            // 将当前节点做头节点，之前的 pre 做为下节点
            pre = head;
            // 一直向链表尾走
            head = next;
        }
        return pre;
    }
}
