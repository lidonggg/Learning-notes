package com.lidong.javaops.algorithm.linkedlist;

/**
 * @author Ls J
 * @date 2019/4/30 3:01 PM
 * 单链表反转
 */
public class LinkedListReserve {

    /**
     * 单链表反转非递归方法
     *
     * @param head 头结点
     * @return 反转后的头结点
     */
    private static ListNode reserve1(ListNode head) {
        if (head == null) {
            return null;
        }

        // 记录反转之后的链表
        ListNode pre = null;
        ListNode next;

        while (head != null) {
            next = head.next;
            // 原地更新参数
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }

    /**
     * 单链表反转递归法
     * 利用递归走到链表的末端，然后再更新每一个node的next
     *
     * @param node 当前递归到的节点
     * @return 最后返回原链表最后一个节点
     */
    private static ListNode reserve2(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }

        ListNode next = node.next;
        node.next = null;
        ListNode reserveRest = reserve2(next);
        next.next = node;

        return reserveRest;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode head = reserve1(node1);

        while (head != null) {
            // 4 3 2 1
            System.out.print(head.n + " ");
            head = head.next;
        }
    }
}
