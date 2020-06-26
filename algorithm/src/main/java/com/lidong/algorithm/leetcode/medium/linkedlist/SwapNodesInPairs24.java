package com.lidong.algorithm.leetcode.medium.linkedlist;

/**
 * 两两交换链表中的节点（中等-24）
 * 中文链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 * <p>
 * 问题描述：
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * @author Ls J
 * @date 2020/6/26 4:28 PM
 */
public class SwapNodesInPairs24 {

    /**
     * 方法一：递归
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：37.5 MB，在所有 Java 提交中击败了 6.35% 的用户
     *
     * @param head head
     * @return new head
     */
    public ListNode swapPairs(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }

        ListNode newHead = head.next;
        ListNode nextGroup = head.next.next;
        newHead.next = head;
        head.next = swapPairs(nextGroup);
        return newHead;
    }

    /**
     * 方法二：迭代
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：37.5 MB，在所有 Java 提交中击败了 6.35% 的用户
     *
     * @param head head
     * @return new head
     */
    public ListNode swapPairs2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prevNode = dummy;

        while ((head != null) && (head.next != null)) {
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            // 交换节点
            prevNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            // 下一组
            prevNode = firstNode;
            head = firstNode.next;
        }

        return dummy.next;
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
