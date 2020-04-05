package com.lidong.algorithm.leetcode.easy.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ls J
 * @date 2020/4/5 11:09 AM
 * 二叉树的最小深度（简单-111）
 * 问题描述：
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最小深度  2.
 */
public class MinDepth111 {

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 1;
        if (root.left == null && root.right == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        // root 节点不算是叶子节点，所以这里我们从第二层开始遍历
        if (root.right != null) {
            q.add(root.right);
        }
        if (root.left != null) {
            q.add(root.left);
        }
        res = 2;
        while (!q.isEmpty()) {
            int len = q.size();
            // 对于每一层，都查看是否有叶子节点，如果有直接返回即可
            for (int i = 0; i < len; ++i) {
                TreeNode curNode = q.poll();
                if (curNode.right == null && curNode.left == null) {
                    return res;
                }
                if (curNode.left != null) {
                    q.add(curNode.left);
                }
                if (curNode.right != null) {
                    q.add(curNode.right);
                }
            }
            res++;

        }
        return -1;
    }

    /**
     * dfs
     *
     * @param root node
     * @return int
     */
    private int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        int minDepth = Integer.MAX_VALUE;

        if (root.left != null) {
            minDepth = Math.min(minDepth1(root.left), minDepth);
        }
        if (root.right != null) {
            minDepth = Math.min(minDepth1(root.right), minDepth);
        }

        return minDepth + 1;
    }

    private class TreeNode {
        int val;

        TreeNode left;

        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
