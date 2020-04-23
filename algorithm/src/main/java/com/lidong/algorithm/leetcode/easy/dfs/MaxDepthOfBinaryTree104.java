package com.lidong.algorithm.leetcode.easy.dfs;

/**
 * @author ls J
 * @date 2020/4/23 17:28
 * 二叉树最大深度（简单-104）
 * 问题描述：
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 */
public class MaxDepthOfBinaryTree104 {

    /**
     * dfs
     * <p>
     * 本题也可以利用 bfs 层次遍历，记录最大深度
     *
     * @param root node
     * @return maxHeight
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    class TreeNode {
        int val;

        TreeNode left;

        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
