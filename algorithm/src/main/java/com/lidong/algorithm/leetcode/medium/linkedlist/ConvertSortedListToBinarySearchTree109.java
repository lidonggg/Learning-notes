package com.lidong.algorithm.leetcode.medium.linkedlist;

/**
 * 有序链表转换二叉搜索树（中等-109）
 * 中文链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/
 * <p>
 * 问题描述：
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 示例:
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 * <p>      0
 * <p>     / \
 * <p>   -3   9
 * <p>   /   /
 * <p> -10  5
 *
 * @author ls J
 * @date 2020/6/24 16:11
 */
public class ConvertSortedListToBinarySearchTree109 {

    /**
     * 递归找中间节点，让它成为子树的根节点
     * 关键是要找到中间节点的前一个节点，然后断开它与中间节点的连接
     * 这里使用快慢指针的方式找中间节点，其实也可以先遍历一遍链表，获取链表的长度，然后取中点
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 72.58% 的用户
     * 内存消耗：41 MB，在所有 Java 提交中击败了 13.33% 的用户
     *
     * @param head head
     * @return root ndoe
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode mid = findMidNode(head);
        if (mid == head) {
            return new TreeNode(head.val);
        }
        TreeNode root = new TreeNode(mid.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);

        return root;
    }

    /**
     * 使用快慢指针找到中间节点
     *
     * @param head head
     * @return mid node
     */
    private ListNode findMidNode(ListNode head) {
        // 用来保存中间节点的前一个节点
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // important！！！ 断开前面的节点与中间节点的连接
        if (prev != null) {
            prev.next = null;
        }

        return slow;
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

    private static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
