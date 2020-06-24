package com.lidong.algorithm.leetcode.medium.linkedlist;

/**
 * 奇偶链表（中等-328）
 * 中文链接：https://leetcode-cn.com/problems/odd-even-linked-list/
 * <p>
 * 问题描述：
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * <p>
 * 示例 2:
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * <p>
 * 说明:
 * - 应当保持奇数节点和偶数节点的相对顺序。
 * - 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 *
 * @author ls J
 * @date 2020/6/24 17:48
 */
public class OddEvenLinkedList328 {

    /**
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.7 MB，在所有 Java 提交中击败了 8.70% 的用户
     *
     * @param head head
     * @return new head
     */
    public ListNode oddEvenList(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode even = head, odd = head.next;
        // 保存奇数链表的第一个节点
        ListNode oddStash = odd;
        while (null != even.next && null != even.next.next) {
            // 构造偶数链表
            even.next = even.next.next;
            even = even.next;
            // 构造奇数链表
            if (null != odd) {
                odd.next = even.next;
                odd = odd.next;
            }
        }

        // 最后一个偶数节点的下一个节点要指向第一个奇数节点
        even.next = oddStash;
        return head;
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
