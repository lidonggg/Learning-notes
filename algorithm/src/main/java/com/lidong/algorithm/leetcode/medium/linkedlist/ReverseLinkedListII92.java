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

    /**
     * 方法二：递归
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：37.1 MB，在所有 Java 提交中击败了 8.70% 的用户
     *
     * @param head head
     * @param m    m
     * @param n    n
     * @return new head
     */
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }
        head.next = reverseBetween2(head.next, m - 1, n - 1);
        return head;
    }

    /**
     * 记录后驱节点
     */
    private ListNode successor = null;

    /**
     * 递归反转前 n 个节点
     *
     * @param head head
     * @param n    n
     * @return new head
     */
    private ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        // 让反转之后的 head 节点（变成了反转部分的最后一个节点）和后面的节点连起来
        head.next = successor;
        return last;
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
