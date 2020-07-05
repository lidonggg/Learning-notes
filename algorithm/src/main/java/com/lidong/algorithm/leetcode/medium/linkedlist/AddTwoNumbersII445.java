package com.lidong.algorithm.leetcode.medium.linkedlist;

import java.util.Stack;

/**
 * 两数相加 II（中等-445）
 * 中文链接：https://leetcode-cn.com/problems/add-two-numbers-ii
 * <p>
 * 问题描述：
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 进阶：
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * <p>
 * 示例：
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 *
 * @author Ls J
 * @date 2020/7/5 3:43 PM
 */
public class AddTwoNumbersII445 {

    /**
     * 借助栈来实现
     * <p>
     * 执行用时：5 ms，在所有 Java 提交中击败了 65.36% 的用户
     * 内存消耗：40.5 MB，在所有 Java 提交中击败了 95.83% 的用户
     *
     * @param l1 l1
     * @param l2 l2
     * @return node
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        while (null != l1) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (null != l2) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        int left = 0;

        ListNode resHair = new ListNode(-1);
        ListNode tmp = resHair;

        while (!s1.isEmpty() || !s2.isEmpty()) {
            int sum = left;
            if (!s1.isEmpty()) {
                sum += s1.pop();
            }
            if (!s2.isEmpty()) {
                sum += s2.pop();
            }
            left = sum / 10;
            tmp.next = new ListNode(sum % 10);
            tmp = tmp.next;
        }
        if (left != 0) {
            tmp.next = new ListNode(left);
        }
        return reverse(resHair.next);
    }

    /**
     * 链表反转
     *
     * @param head head
     * @return new head
     */
    private ListNode reverse(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode next = head.next;
        head.next = null;
        ListNode reversedList = reverse(next);
        next.next = head;
        return reversedList;
    }

    private static class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }
    }
}
