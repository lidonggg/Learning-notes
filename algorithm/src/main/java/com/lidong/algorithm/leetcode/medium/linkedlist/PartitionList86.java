package com.lidong.algorithm.leetcode.medium.linkedlist;

/**
 * 分隔链表（中等-86）
 * 中文链接：https://leetcode-cn.com/problems/partition-list
 * <p>
 * 问题描述：
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 * <p>
 * 示例:
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 *
 * @author Ls J
 * @date 2020/7/7 12:54 AM
 */
public class PartitionList86 {

    /**
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.1 MB，在所有 Java 提交中击败了 5.55% 的用户
     *
     * @param head head
     * @param x    x
     * @return new head
     */
    public ListNode partition(ListNode head, int x) {

        // 保存所有值小于 x 的链表节点
        ListNode beforeHead = new ListNode(0);
        ListNode before = beforeHead;
        // 保存所有值大于等于 x 的链表节点
        ListNode afterHead = new ListNode(0);
        ListNode after = afterHead;

        while (head != null) {
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }

            head = head.next;
        }

        after.next = null;
        before.next = afterHead.next;

        return beforeHead.next;
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
