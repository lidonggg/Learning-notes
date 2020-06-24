package com.lidong.algorithm.leetcode.hard.linkedlist;

/**
 * k 个一组翻转链表（困难-25）
 * 中文链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 * <p>
 * 问题描述：
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
 * <p>
 * 示例：
 * 给你这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 说明：
 * - 你的算法只能使用常数的额外空间。
 * - 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * @author ls J
 * @date 2020/6/24 10:05
 */
public class ReverseNodesInKGroup25 {

    /**
     * 方法一：递归
     * 链表翻转的变形而已
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：40 MB，在所有 Java 提交中击败了 7.32% 的用户
     *
     * @param head head
     * @param k    k
     * @return ListNode
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (null == head) {
            return null;
        }
        ListNode last = head;
        for (int i = 0; i < k; ++i) {
            if (null == last) {
                return head;
            }
            last = last.next;
        }
        // 返回当前组翻转后的头结点
        ListNode newHead = reverse(head, last);
        // 翻转后当前组之前的头结点变成了翻转后的尾结点
        // 所以只需要将 head 的 next 指针指向下一组的头结点即可
        head.next = reverseKGroup(last, k);
        // 返回每组的头结点
        return newHead;
    }

    private ListNode reverse(ListNode first, ListNode last) {
        ListNode pre = null, next;

        while (first != last) {
            next = first.next;
            first.next = pre;
            pre = first;
            first = next;
        }
        // 返回新的头节点
        return pre;
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
