package com.lidong.algorithm.leetcode.easy.tree;

/**
 * 翻转二叉树（简单-226）
 * 中文链接：https://leetcode-cn.com/problems/invert-binary-tree/
 * 问题描述：
 * 翻转一棵二叉树
 *
 * @author Ls J
 * @date 2020/5/4 4:56 PM
 */
public class InvertBinaryTree226 {

    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }

    private void invert(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
        invert(node.left);
        invert(node.right);
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


