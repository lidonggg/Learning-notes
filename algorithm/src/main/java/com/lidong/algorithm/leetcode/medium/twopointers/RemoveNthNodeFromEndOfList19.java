package com.lidong.algorithm.leetcode.medium.twopointers;

/**
 * 删除链表中的倒数第 N 个节点
 * 中文链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * <p>
 * 问题描述：
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 *
 * @author ls J
 * @date 2020/6/19 10:41
 */
public class RemoveNthNodeFromEndOfList19 {

    /**
     * 双指针
     *
     * @param head 头结点
     * @param n    n
     * @return 删除后的头结点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 添加一个哨兵节点，减少一些边界处理
        ListNode hair = new ListNode(-1);
        hair.next = head;

        ListNode first = hair;
        for (int i = 0; i <= n; ++i) {
            first = first.next;
        }
        ListNode second = hair;

        while (first != null) {
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;

        return hair.next;
    }

    private static class ListNode {

        int val;

        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}
