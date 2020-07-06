package com.lidong.algorithm.leetcode.medium.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * 链表组件（中等-817）
 * 中文链接：https://leetcode-cn.com/problems/linked-list-components
 * <p>
 * 问题描述：
 * 给定链表头结点 head，该链表上的每个结点都有一个唯一的整型值 。
 * 同时给定列表 G，该列表是上述链表中整型值的一个子集。
 * 返回列表 G 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 G 中）构成的集合。
 * <p>
 * 示例 1：
 * 输入:
 * head: 0->1->2->3
 * G = [0, 1, 3]
 * 输出: 2
 * 解释:
 * 链表中,0 和 1 是相连接的，且 G 中不包含 2，所以 [0, 1] 是 G 的一个组件，同理 [3] 也是一个组件，故返回 2。
 * <p>
 * 示例 2：
 * 输入:
 * head: 0->1->2->3->4
 * G = [0, 3, 1, 4]
 * 输出: 2
 * 解释:
 * 链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。
 * <p>
 * 提示：
 * 如果 N 是给定链表 head 的长度，1 <= N <= 10000。
 * 链表中每个结点的值所在范围为 [0, N - 1]。
 * 1 <= G.length <= 10000
 * G 是链表中所有结点的值的一个子集.
 *
 * @author ls J
 * @date 2020/7/6 19:37
 */
public class LinkedListComponents817 {

    /**
     * 一次遍历链表
     * <p>
     * 执行用时：7 ms，在所有 Java 提交中击败了 90.08% 的用户
     * 内存消耗：40.4 MB，在所有 Java 提交中击败了 25.00% 的用户
     *
     * @param head head
     * @param g    g
     * @return num
     */
    public int numComponents(ListNode head, int[] g) {
        Set<Integer> gSet = new HashSet<>();
        for (int i : g) {
            gSet.add(i);
        }
        int res = 0;
        ListNode tmp = head;
        while (null != tmp) {
            // 如果 g 中存在当前值，res + 1
            if (gSet.contains(tmp.val)) {
                res++;
                // 遍历直到 g 中不存在当前节点值
                while (null != tmp && gSet.contains(tmp.val)) {
                    tmp = tmp.next;
                }
            }
            if (null != tmp) {
                tmp = tmp.next;
            }
        }

        return res;
    }

    private static class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }
    }
}
