package com.lidong.algorithm.leetcode.medium.linkedlist;

/**
 * 反转链表 II（中等-92）
 * 中文链接：https://leetcode-cn.com/problems/reverse-linked-list-ii/
 * <p>
 * 问题描述：
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * <p>
 * 示例:
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * @author ls J
 * @date 2020/6/24 17:02
 */
public class ReverseLinkedListII92 {

    /**
     * 与单链表反转唯一不同的是需要记录一下前置节点和后置节点
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：37.6 MB，在所有 Java 提交中击败了 8.70% 的用户
     *
     * @param head head
     * @param m    m
     * @param n    n
     * @return new Head
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode hair = new ListNode(-1);
        hair.next = head;
        int idx = 1;
        // 前置节点
        ListNode preNode = hair;
        while (idx < m) {
            idx++;
            preNode = preNode.next;
        }
        ListNode revHead = preNode.next;
        if (null == revHead) {
            return head;
        }
        ListNode pre = null, next = null;
        // 记录反转之前的第一个反转的节点
        // 它在被反转之后需要指向后置节点
        ListNode stashRevHead = revHead;
        while (idx <= n) {
            idx++;
            next = revHead.next;
            revHead.next = pre;
            pre = revHead;
            revHead = next;
        }
        // 前置节点的下一个节点指向反转那部分链表反转后的头结点
        preNode.next = pre;
        // 反转那部分反转后的最后一个节点指向后置节点，也就是反转过程中的 next 指针
        stashRevHead.next = next;

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
