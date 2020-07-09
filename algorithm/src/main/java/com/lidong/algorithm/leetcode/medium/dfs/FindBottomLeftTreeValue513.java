package com.lidong.algorithm.leetcode.medium.dfs;

/**
 * 找树左下角的值（中等-513）
 * 中文链接：https://leetcode-cn.com/problems/find-bottom-left-tree-value/
 * <p>
 * 问题描述：
 * 给定一个二叉树，在树的最后一行找到最左边的值。
 * <p>
 * 注意；
 * 你可以假设树的根结点不为 null
 *
 * @author Ls J
 * @date 2020/6/21 11:17 AM
 */
public class FindBottomLeftTreeValue513 {

    private int maxDepth;

    private int lastVal;

    /**
     * dfs
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.6 MB，在所有 Java 提交中击败了 8.70% 的用户
     *
     * @param root 根结点
     * @return res
     */
    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 1);
        return lastVal;
    }

    private void dfs(TreeNode root, int curDepth) {
        if (curDepth > maxDepth) {
            maxDepth = curDepth;
            lastVal = root.val;
        }
        if (null != root.left) {
            dfs(root.left, curDepth + 1);
        }
        if (null != root.right) {
            dfs(root.right, curDepth + 1);
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
