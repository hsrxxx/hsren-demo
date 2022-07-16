package com.hsren.algorithm.leetcode;

import com.hsren.dataStructure.ListNode;

/**
 * 2.两数相加
 *      给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *      请你将两个数相加，并以相同形式返回一个表示和的链表。
 *      你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *      输入：l1 = [2,4,3], l2 = [5,6,4]
 *      输出：[7,0,8]
 *      解释：342 + 465 = 807.
 *
 *      输入：l1 = [0], l2 = [0]
 *      输出：[0]
 *
 *      输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 *      输出：[8,9,9,9,0,0,0,1]
 *
 * 提示：
 *      每个链表中的节点数在范围 [1, 100] 内
 *      0 <= Node.val <= 9
 *      题目数据保证列表表示的数字不含前导零
 *
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 */
public class _2_addTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9)))))));
        ListNode l2 = new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9))));
        addTwoNumbers1(l1,l2).print();
        addTwoNumbers2(l1,l2).print();
    }

    /**
     * 硬磕失败
     * [1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1]
     * [5,6,4]
     * 长度不够，精度不够
     * @param l1 链表1
     * @param l2 链表2
     * @return 返回链表1、链表2反转后相加在反转的链表
     */
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode headerResult = result;
        ListNode newResult;
        ListNode oldResult = new ListNode();
        long intResult;
        char[] charResult;
        StringBuilder strL1 = new StringBuilder();
        String strL2 = "";
        while (l2 != null || l1 != null) {
            if (l1 != null) {
                strL1.append(String.valueOf(l1.val));
                l1 = l1.next;
            }
            if (l2 != null) {
                strL2 += String.valueOf(l2.val);
                l2 = l2.next;
            }
        }
        intResult = Long.parseLong(new StringBuilder(strL1.toString()).reverse().toString()) + Long.parseLong(new StringBuilder(strL2).reverse().toString());
        charResult = new StringBuilder(String.valueOf(intResult)).reverse().toString().toCharArray();
        for (char c : charResult){
            result.val = Character.getNumericValue(c);
            oldResult = result;
            newResult = new ListNode();
            result.next = newResult;
            result = newResult;
        }
        oldResult.next = null;
        result = headerResult;
        return result;
    }

    /**
     * 参考：顺序进位计算
     * @param l1 链表1
     * @param l2 链表2
     * @return 返回链表1、链表2反转后相加在反转的链表
     */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2){
        ListNode root = new ListNode();
        ListNode cursor = root;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0){
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sumVal = l1Val + l2Val + carry;
            // 获取进位 如大于等于10则为1，否则为0
            // Java 中"/“是取整，”%"是取余
            carry = sumVal / 10;
            // 获取个位
            cursor.next = new ListNode(sumVal % 10);
            cursor = cursor.next;

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        return root.next;
    }

}