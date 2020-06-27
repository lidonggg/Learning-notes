package com.lidong.algorithm.leetcode.medium.linkedlist;

/**
 * 对链表进行插入排序（中等-147）
 * 中文链接：https://leetcode-cn.com/problems/insertion-sort-list/
 * <p>
 * 问题描述：
 * 插入排序算法：
 * 1.插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 2.每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 3.重复直到所有输入数据插入完为止。
 *
 * @author Ls J
 * @date 2020/6/27 2:47 PM
 */
public class InsertionSortList147 {

    /**
     * 方法一
     * <p>
     * 执行用时：26 ms，在所有 Java 提交中击败了 32.89% 的用户
     * 内存消耗：39.5 MB，在所有 Java 提交中击败了 6.67% 的用户
     *
     * @param head head
     * @return new head
     */
    public ListNode insertionSortList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode hair = new ListNode(-1);
        hair.next = head;

        // pt - 未排序部分的第一个节点
        // pre - 记录要插入位置的前一个节点，将它的 next 指针指向要插入的节点
        // tmp - 记录待插入节点与已排序节点的比较的位置
        ListNode pt = head.next, pre = hair, tmp = hair.next;
        head.next = null;

        while (null != pt) {
            while (null != tmp && tmp.val < pt.val) {
                pre = tmp;
                tmp = tmp.next;
            }
            // 指针重组
            ListNode stash = pt.next;
            // 插入新节点
            pre.next = pt;
            pt.next = tmp;
            // 后移待插入节点
            pt = stash;
            // 重置 pre 和 tmp
            pre = hair;
            tmp = hair.next;
        }

        return hair.next;
    }

    /**
     * 方法二：时间复杂度优化，排除已排好序的相邻节点
     * <p>
     * 执行用时：3 ms，在所有 Java 提交中击败了 98。58% 的用户
     * 内存消耗：39.2 MB，在所有 Java 提交中击败了 6.67% 的用户
     *
     * @param head head
     * @return new head
     */
    public ListNode insertionSortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode hair = new ListNode(0);
        ListNode pre = head;
        // 待插入节点
        ListNode cur = head.next;
        hair.next = head;
        while (cur != null) {
            // 本来就有序
            if (pre.val <= cur.val) {
                pre = cur;
                cur = cur.next;
            } else {
                ListNode p = hair;
                // 找到一个位置使得 p < cur < p.next
                while (p.next.val < cur.val) {
                    p = p.next;
                }
                // 将 cur 插入到 p 和 p.next 之间
                // 因为 cur 被插到前面，pre 的指针需要跳过 cur
                pre.next = cur.next;
                cur.next = p.next;
                p.next = cur;
                // 完成插入后，cur 回到 pre 后面
                cur = pre.next;
            }
        }
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
    }
}
