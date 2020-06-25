package com.lidong.algorithm.leetcode.medium.linkedlist;

/**
 * 旋转链表（中等-61）
 * 中文链接：https://leetcode-cn.com/problems/rotate-list/
 * <p>
 * 问题描述：
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * <p>
 * 示例 2:
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 *
 * @author Ls J
 * @date 2020/6/26 12:01 AM
 */
public class RotateList61 {

    /**
     * 方法一：本质上就是把后 k % n 个节点移到前面
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 83.18% 的用户
     * 内存消耗：39.5 MB，在所有 Java 提交中击败了 5.41% 的用户
     *
     * @param head head
     * @param k    k
     * @return new head
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        int n = 0;
        ListNode tmp = head;
        ListNode tail = null;
        while (null != tmp) {
            n++;
            tail = tmp;
            tmp = tmp.next;
        }
        int nk = k % n;
        if (nk == 0) {
            return head;
        }
        tmp = head;
        ListNode pre = null;
        for (int i = 0; i < n - nk; ++i) {
            pre = tmp;
            tmp = tmp.next;
        }
        // pre 不可能为 null，因为上面已经提前处理了 nk == 0 的情况
        pre.next = null;
        tail.next = head;

        return tmp;
    }

    /**
     * 方法二：构造一个环，然后在环上移动指针，避免需要记录一些额外的节点信息
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.2 MB，在所有 Java 提交中击败了 5.41% 的用户
     *
     * @param head head
     * @param k    k
     * @return new head
     */
    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        int n = 1;
        ListNode tmp = head;
        while (null != tmp.next) {
            n++;
            tmp = tmp.next;
        }
        // 构造出一个环
        tmp.next = head;
        tmp = head;
        for (int i = 0; i < n - k % n - 1; ++i) {
            tmp = tmp.next;
        }
        ListNode newHead = tmp.next;
        tmp.next = null;
        return newHead;
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
