package com.lidong.algorithm.leetcode.medium.linkedlist;

/**
 * 两数相加（中等-2）
 * 中文链接：https://leetcode-cn.com/problems/add-two-numbers/
 * <p>
 * 问题描述:
 * 给出两个非空的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * @author ls J
 * @date 2020/6/19 14:37
 */
public class AddTwoNumbers2 {

    /**
     * 按位操作，并记录进位情况
     * <p>
     * 执行用时：2 ms，在所有 Java 提交中击败了 99.90% 的用户
     * 内存消耗： 39.7 MB，在所有 Java 提交中击败了 94.74% 的用户
     *
     * @param l1 l1
     * @param l2 l2
     * @return head of res
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode hair = new ListNode(-1);
        ListNode tmp = hair;
        // 记录进位
        int left = 0;
        while (l1 != null && l2 != null) {
            int i1 = l1.val, i2 = l2.val;
            int sum = i1 + i2 + left;
            left = sum / 10;
            if (sum <= 9) {
                tmp.next = new ListNode(sum);
            } else {
                tmp.next = new ListNode(sum % 10);
            }
            tmp = tmp.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int sum = l1.val + left;
            left = sum / 10;
            if (sum <= 9) {
                tmp.next = new ListNode(sum);
            } else {
                tmp.next = new ListNode(sum % 10);
            }
            tmp = tmp.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            int sum = l2.val + left;
            left = sum / 10;
            if (sum <= 9) {
                tmp.next = new ListNode(sum);
            } else {
                tmp.next = new ListNode(sum % 10);
            }
            tmp = tmp.next;
            l2 = l2.next;
        }
        if (left == 1) {
            tmp.next = new ListNode(1);
        }
        return hair.next;
    }

    /**
     * 简写
     * <p>
     * 执行用时：2 ms，在所有 Java 提交中击败了 99.90% 的用户
     * 内存消耗： 39.9 MB，在所有 Java 提交中击败了 94.26% 的用户
     *
     * @param l1 l1
     * @param l2 l1
     * @return head of res
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode hair = new ListNode(-1);
        ListNode tmp = hair;
        // 记录进位
        int left = 0;
        while (l1 != null || l2 != null) {
            int i1 = (null != l1) ? l1.val : 0;
            int i2 = (null != l2) ? l2.val : 0;
            int sum = i1 + i2 + left;
            left = sum / 10;
            tmp.next = new ListNode(sum % 10);
            if (null != l1) {
                l1 = l1.next;
            }
            if (null != l2) {
                l2 = l2.next;
            }
            tmp = tmp.next;
        }

        if (left == 1) {
            tmp.next = new ListNode(1);
        }
        return hair.next;
    }

    private static class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
