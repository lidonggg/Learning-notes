package com.lidong.algorithm.leetcode.medium.tree;

/**
 * 二叉树展开为链表（中等-114）
 * 中文链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 * <p>
 * 问题描述：
 * 给定一个二叉树，原地将它展开为一个单链表。
 * <p>
 * 例如，给定二叉树
 * <p>    1
 * <p>   / \
 * <p>  2   5
 * <p> / \   \
 * <p>3   4   6
 * 将其展开为：
 * <p>1
 * <p> \
 * <p>  2
 * <p>   \
 * <p>    3
 * <p>     \
 * <p>      4
 * <p>       \
 * <p>        5
 * <p>         \
 * <p>          6
 *
 * @author Ls J
 * @date 2020/6/26 2:57 PM
 */
public class FlattenBinaryTreeToLinkedList114 {

    /**
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.5 MB，在所有 Java 提交中击败了 6.67% 的用户
     *
     * @param root root node
     */
    public void flatten(TreeNode root) {
        while (null != root) {
            if (null != root.left) {
                // 寻找左子树的最右侧节点
                TreeNode lr = root.left;
                while (null != lr.right) {
                    lr = lr.right;
                }
                // 将右子树接在左子树最右侧节点的后面
                lr.right = root.right;
                // 将左子树移到右子树
                root.right = root.left;
                // 释放左子树
                root.left = null;
            }
            root = root.right;
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
