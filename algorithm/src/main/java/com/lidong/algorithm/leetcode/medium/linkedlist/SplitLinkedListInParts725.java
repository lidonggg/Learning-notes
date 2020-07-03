package com.lidong.algorithm.leetcode.medium.linkedlist;

/**
 * 分割链表（中等-725）
 * 中文链接：https://leetcode-cn.com/problems/split-linked-list-in-parts/
 * <p>
 * 问题描述：
 * 给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。
 * 每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。
 * 这k个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。
 * 返回一个符合上述规则的链表的列表。
 * 举例： 1->2->3->4, k = 5 // 5 结果 [ [1], [2], [3], [4], null ]
 * <p>
 * 示例 1：
 * 输入:
 * root = [1, 2, 3], k = 5
 * 输出: [[1],[2],[3],[],[]]
 * 解释:
 * 输入输出各部分都应该是链表，而不是数组。
 * 例如, 输入的结点 root 的 val= 1, root.next.val = 2, \root.next.next.val = 3, 且 root.next.next.next = null。
 * 第一个输出 output[0] 是 output[0].val = 1, output[0].next = null。
 * 最后一个元素 output[4] 为 null, 它代表了最后一个部分为空链表。
 * <p>
 * 示例 2：
 * 输入:
 * root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
 * 输出: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
 * 解释:
 * 输入被分成了几个连续的部分，并且每部分的长度相差不超过1.前面部分的长度大于等于后面部分的长度。
 *  
 * 提示:
 * - root 的长度范围： [0, 1000].
 * - 输入的每个节点的大小范围：[0, 999].
 * - k 的取值范围： [1, 50].
 *
 * @author ls J
 * @date 2020/7/3 15:51
 */
public class SplitLinkedListInParts725 {

    /**
     * 假设：
     * ite -- len / k 整数部分
     * left -- len / k 剩余部分
     * 则 (ite + 1) * left + ite * (k - left) = left + ite * k = len
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：40 MB，在所有 Java 提交中击败了 6.67%  的用户
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param root root
     * @param k    k
     * @return ListNode[]
     */
    public ListNode[] splitListToParts(ListNode root, int k) {
        int len = 0;
        ListNode tmp = root;
        while (null != tmp) {
            len++;
            tmp = tmp.next;
        }

        ListNode[] res = new ListNode[k];

        int ite = len / k;
        int left = len % k;
        tmp = root;
        // pre 记录每一部分的最后一个元素，用于分割链表
        ListNode pre = null;
        // 前面 left 个分割链表中每个链表含有 ite 个元素
        for (int i = 0; i < left; ++i) {
            res[i] = tmp;
            int pr = 0;
            while (pr < ite + 1 && null != tmp) {
                pre = tmp;
                tmp = tmp.next;
                pr++;
            }
            // 分割
            pre.next = null;
        }
        pre = null;
        // 后面每个部分含有 ite 个元素
        for (int i = left; i < k; ++i) {
            res[i] = tmp;
            int pr = 0;
            while (pr < ite && null != tmp) {
                pre = tmp;
                tmp = tmp.next;
                pr++;
            }
            if (null != pre) {
                pre.next = null;
            }
        }

        return res;
    }

    /**
     * 将上述两个 for 循环合并在一起
     * <p>
     * 假设：
     * ite -- len / k 整数部分
     * left -- len / k 剩余部分
     * 则 (ite + 1) * left + ite * (k - left) = left + ite * k = len
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：40 MB，在所有 Java 提交中击败了 6.67%  的用户
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param root root
     * @param k    k
     * @return ListNode[]
     */
    public ListNode[] splitListToParts2(ListNode root, int k) {
        ListNode cur = root;
        int len = 0;
        while (cur != null) {
            cur = cur.next;
            len++;
        }

        int ite = len / k, left = len % k;

        ListNode[] res = new ListNode[k];
        cur = root;
        for (int i = 0; i < k; ++i) {
            ListNode head = cur;
            for (int j = 0; j < ite + (i < left ? 1 : 0) - 1; ++j) {
                if (cur != null) {
                    cur = cur.next;
                }
            }
            if (cur != null) {
                ListNode prev = cur;
                cur = cur.next;
                prev.next = null;
            }
            res[i] = head;
        }
        return res;
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
