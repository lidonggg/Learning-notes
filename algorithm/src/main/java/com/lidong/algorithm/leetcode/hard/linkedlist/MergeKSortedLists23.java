package com.lidong.algorithm.leetcode.hard.linkedlist;

/**
 * 合并 k 个有序链表（困难-23）
 * 中文链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * <p>
 * 问题描述：
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * @author ls J
 * @date 2020/7/9 10:15
 */
public class MergeKSortedLists23 {

    private ListNode[] lists;

    /**
     * 归并排序
     * 利用归并排序的思想，两两合并链表
     * <p>
     * 执行用时：2 ms，在所有 Java 提交中击败了 94.90% 的用户
     * 内存消耗：41.8 MB，在所有 Java 提交中击败了 48.53% 的用户
     *
     * k -- 链表个数，n -- 每个链表最长长度
     * 渐进时间复杂度：O(kn*logk)
     * 空间复杂度：O(logk)
     *
     * @param lists lists
     * @return head
     */
    public ListNode mergeKLists(ListNode[] lists) {
        this.lists = lists;
        int l = 0, r = lists.length - 1;
        return merge(l, r);
    }

    /**
     * merge
     *
     * @param l l
     * @param r r
     * @return sorted list head
     */
    private ListNode merge(int l, int r) {
        if (l > r) {
            return null;
        }
        // 如果 l = r，只需要返回当前链表就行
        if (l == r) {
            return lists[l];
        }
        int mid = l + ((r - l) >> 1);
        // 操作前一半数据
        ListNode l1 = merge(l, mid);
        // 操作后一半数据
        ListNode l2 = merge(mid + 1, r);
        // 合并前后两部分
        return merge2Lists(l1, l2);
    }

    /**
     * 合并两个有序链表
     *
     * @param l1 l1
     * @param l2 l2
     * @return new head
     */
    private ListNode merge2Lists(ListNode l1, ListNode l2) {
        ListNode hair = new ListNode(-1);
        ListNode tmp = hair;
        while (null != l1 && null != l2) {
            if (l1.val < l2.val) {
                tmp.next = l1;
                l1 = l1.next;
            } else {
                tmp.next = l2;
                l2 = l2.next;
            }
            tmp = tmp.next;
        }
        tmp.next = null == l1 ? l2 : l1;

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
