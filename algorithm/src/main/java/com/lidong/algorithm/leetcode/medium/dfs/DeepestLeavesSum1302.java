package com.lidong.algorithm.leetcode.medium.dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 层数最深的叶子节点的和（中等-1302）
 * 中文链接：https://leetcode-cn.com/problems/deepest-leaves-sum/
 * <p>
 * 问题描述：
 * 给你一棵二叉树，请你返回层数最深的叶子节点的和。
 * <p>
 * 示例：
 * 输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * 输出：15
 *  
 * 提示：
 * - 树中节点数目在 1 到 10^4 之间。
 * - 每个节点的值在 1 到 100 之间。
 *
 * @author ls J
 * @date 2020/6/22 20:35
 */
public class DeepestLeavesSum1302 {

    /**
     * 方法一：bfs
     * 这应该是最直接的方法
     * <p>
     * 执行用时：6 ms，在所有 Java 提交中击败了 34.51% 的用户
     * 内存消耗：40.8 MB，在所有 Java 提交中击败了 6.67% 的用户
     *
     * @param root 根节点
     * @return sum
     */
    public int deepestLeavesSum(TreeNode root) {
        if (null == root) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            // 用来判断当前层是不是最后一层
            boolean bottom = true;
            int sum = 0;
            for (int i = 0; i < len; ++i) {
                TreeNode curNode = queue.poll();
                sum += curNode.val;
                if (null != curNode.left) {
                    if (bottom) {
                        bottom = false;
                    }
                    queue.add(curNode.left);
                }
                if (null != curNode.right) {
                    if (bottom) {
                        bottom = false;
                    }
                    queue.add(curNode.right);
                }
            }
            if (bottom) {
                return sum;
            }
        }
        return 0;
    }

    /**
     * 方法二：dfs
     *
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 98.86% 的用户
     * 内存消耗：40.8 MB，在所有 Java 提交中击败了 6.67% 的用户
     *
     * @param root 根节点
     * @return sum
     */
    public int deepestLeavesSum2(TreeNode root) {
        int maxDepth = maxDepth(root);
        return dfs(root, 0, maxDepth);
    }

    /**
     * dfs
     *
     * @param root     当前根节点
     * @param curDepth 当前深度
     * @param maxDepth 最大深度
     * @return sum
     */
    private int dfs(TreeNode root, int curDepth, int maxDepth) {
        if (null == root) {
            return 0;
        }
        curDepth++;
        if (curDepth == maxDepth) {
            return root.val;
        }

        return dfs(root.left, curDepth, maxDepth) + dfs(root.right, curDepth, maxDepth);
    }

    /**
     * 求最大深度
     *
     * @param root 当前根节点
     * @return max depth
     */
    private int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
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
