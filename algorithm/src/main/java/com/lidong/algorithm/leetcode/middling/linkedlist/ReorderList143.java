package com.lidong.algorithm.leetcode.middling.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 重排链表（中等-143）
 * 中文链接：https://leetcode-cn.com/problems/reorder-list/
 * 问题描述：
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1:
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * <p>
 * 示例 2:
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 *
 * @author ls J
 * @date 2020/5/6 15:00
 */
public class ReorderList143 {

    /**
     * 链表反转
     *
     * @param head 头节点
     * @return 反转之后的头节点
     */
    private ListNode reserve(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode pre = null;
        // 临时节点，保存下一节点的地址
        ListNode next;

        while (head != null) {
            // 暂存下一节点
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }

    /**
     * 方法一，将链表分成前后两部分，后面一部分链表反转，然后合并两个链表即可
     * 链表的分解，利用快慢指针即可
     *
     * @param head 头节点
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        // 找中点，链表分成两个
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode newHead = slow.next;
        slow.next = null;

        // 第二个链表倒置
        newHead = reserve(newHead);

        // 链表节点依次连接
        while (newHead != null) {
            ListNode temp = newHead.next;
            newHead.next = head.next;
            head.next = newHead;
            head = newHead.next;
            newHead = temp;
        }
    }

    /**
     * 方法二，用一个数组保存所有节点，然后改变每个节点的 next
     *
     * @param head 头节点
     */
    public void reorderList2(ListNode head) {
        if (head == null) {
            return;
        }
        // 用一个数组保存
        List<ListNode> nodes = new ArrayList<>();
        while (head != null) {
            nodes.add(head);
            head = head.next;
        }
        int len = nodes.size();
        // 只需要遍历前一半节点即可
        for (int i = 0; i < len >> 1; ++i) {
            ListNode cur = nodes.get(i), curNext = nodes.get(len - i - 1);
            ListNode tmp = cur.next;
            cur.next = curNext;
            if (curNext != tmp) {
                curNext.next = tmp;
            }
        }
        // 中间节点的 next 节点指向 null，防止出现环
        nodes.get(len / 2).next = null;
    }

    /**
     * 方法三，来源于 leetcode 题解，利用递归
     * <a href='https://leetcode-cn.com/problems/reorder-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-34/'>题解</a>
     * 主要思想就是利用递归函数返回当前头元素对应的尾元素，并且将头元素和尾元素之间的链表按要求完成
     *
     * @param head 头节点
     */
    public void reorderList3(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        int len = 0;
        ListNode h = head;
        //求出节点数
        while (h != null) {
            len++;
            h = h.next;
        }

        reorderListHelper(head, len);
    }

    private ListNode reorderListHelper(ListNode head, int len) {
        if (len == 1) {
            ListNode outTail = head.next;
            head.next = null;
            return outTail;
        }
        if (len == 2) {
            ListNode outTail = head.next.next;
            head.next.next = null;
            return outTail;
        }
        // 得到对应的尾节点，并且将头结点和尾节点之间的链表通过递归处理
        ListNode tail = reorderListHelper(head.next, len - 2);
        // 中间链表的头结点
        ListNode subHead = head.next;
        head.next = tail;
        // 上一层 head 对应的 tail
        ListNode outTail = tail.next;
        tail.next = subHead;
        return outTail;
    }
}


class ListNode {

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