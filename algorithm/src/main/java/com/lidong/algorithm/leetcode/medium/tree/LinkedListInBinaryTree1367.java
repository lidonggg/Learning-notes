package com.lidong.algorithm.leetcode.medium.tree;

/**
 * 二叉树中的链表（中等-1367）
 * 中文链接：https://leetcode-cn.com/problems/linked-list-in-binary-tree/
 * 问题描述：
 * 给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。
 * 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
 * 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
 * <p>
 * 示例 1：
 * 输入：head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：true
 * 解释：树中蓝色的节点构成了与链表对应的子路径。
 * <p>
 * 示例 2：
 * 输入：head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：true
 * <p>
 * 示例 3：
 * 输入：head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：false
 * 解释：二叉树中不存在一一对应链表的路径。
 *  
 * 提示：
 * 1.二叉树和链表中的每个节点的值都满足 1 <= node.val <= 100 。
 * 2.链表包含的节点数目在 1 到 100 之间。
 * 3.二叉树包含的节点数目在 1 到 2500 之间。
 *
 * @author Ls J
 * @date 2020/6/29 10:53 PM
 */
public class LinkedListInBinaryTree1367 {

    /**
     * 执行用时：1 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：40.2 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param head head
     * @param root root
     * @return true if found
     */
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (null == root) {
            return null == head;
        }
        return dfs(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    /**
     * dfs 比较
     *
     * @param head 链表当前节点
     * @param root 树当前节点
     * @return true if found
     */
    private boolean dfs(ListNode head, TreeNode root) {
        if (null == head) {
            return true;
        }
        if (null == root) {
            return false;
        }

        if (head.val != root.val) {
            return false;
        }

        return dfs(head.next, root.left) || dfs(head.next, root.right);
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

    private static class TreeNode {

        int val;

        TreeNode left;

        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
