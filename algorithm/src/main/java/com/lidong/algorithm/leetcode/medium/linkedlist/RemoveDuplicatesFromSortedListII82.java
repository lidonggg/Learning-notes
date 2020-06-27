package com.lidong.algorithm.leetcode.medium.linkedlist;

/**
 * 删除排序链表中的重复元素 II（中等-82）
 * 中文链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
 * <p>
 * 问题描述：
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * <p>
 * 示例 1:
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * <p>
 * 示例 2:
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 *
 * @author Ls J
 * @date 2020/6/27 2:26 PM
 */
public class RemoveDuplicatesFromSortedListII82 {

    /**
     * 执行用时：1 ms，在所有 Java 提交中击败了 92.19% 的用户
     * 内存消耗：39.5 MB，在所有 Java 提交中击败了 6.67% 的用户
     *
     * @param head head
     * @return new head
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }

        ListNode hair = new ListNode(-1);
        hair.next = head;
        // 当前已保留的最后一个节点
        ListNode preNode = hair;
        ListNode pr = head;
        while (null != pr && null != pr.next) {
            int count = 1;
            // 计算当前节点值出现的次数
            while (null != pr.next && pr.val == pr.next.val) {
                pr = pr.next;
                count++;
            }
            // 如果出现了大于一次，那么删除这些节点
            if (count > 1) {
//                preNode.next = pr.next;
//                pr = preNode.next;
                // 与上面两行代码等价
                pr = preNode.next = pr.next;
            } else {
                // 否则当前值只出现了一次，当前节点可以加入到保留节点中，往后遍历下一个节点
                preNode = pr;
                pr = pr.next;
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
