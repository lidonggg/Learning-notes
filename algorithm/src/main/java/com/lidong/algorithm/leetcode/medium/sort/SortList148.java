package com.lidong.algorithm.leetcode.medium.sort;

/**
 * 排序链表（中等-148）
 * 中文链接：https://leetcode-cn.com/problems/sort-list
 * <p>
 * 问题描述：
 * 在 O(nlogn) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * <p>
 * 示例 1:
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * <p>
 * 示例 2:
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * @author ls J
 * @date 2020/7/7 15:10
 */
public class SortList148 {

    /**
     * 归并排序，从顶至下
     * <p>
     * 执行用时：3 ms，在所有 Java 提交中击败了 99.25% 的用户
     * 内存消耗：41.8 MB，在所有 Java 提交中击败了 5.88% 的用户
     * <p>
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     *
     * @param head head
     * @return new head
     */
    public ListNode sortList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        // 找中点，分割成两块
        ListNode slow = head, fast = head.next;
        while (null != fast && null != fast.next) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode tmp = slow.next;
        // 拆分
        slow.next = null;
        // 递归左右部分，分别排好序
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);

        ListNode hair = new ListNode(-1);
        ListNode hpr = hair;
        // 对左右两部分进行 merge 操作
        while (null != left && null != right) {
            if (left.val < right.val) {
                hpr.next = left;
                left = left.next;
            } else {
                hpr.next = right;
                right = right.next;
            }
            hpr = hpr.next;
        }
        // 追加剩余部分
        hpr.next = null == left ? right : left;

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
