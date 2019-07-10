package com.lidong.algorithm.leetcode.easy;

/**
 * @author ls J
 * @date 2019/7/10 2:34 PM
 * 删除链表中的节点（简单-237）
 * 问题描述：
 * 编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
 * <ul>
 * <li>链表至少包含两个节点；</li>
 * <li>链表中的所有的节点的值都是唯一的；</li>
 * <li>给定的节点为非末尾节点且是一个有效的节点；</li>
 * <li>不要有返回值</li>
 * </ul>
 */
public class DeleteNode237 {

    /**
     * 只需要让当前节点的只招3指向下一个节点就行了
     *
     * @param node node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] args) {

    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
