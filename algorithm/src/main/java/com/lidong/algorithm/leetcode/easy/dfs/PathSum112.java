package com.lidong.algorithm.leetcode.easy.dfs;

/**
 * 路径总和（简单-112）
 * 中文链接：https://leetcode-cn.com/problems/path-sum
 * <p>
 * 问题描述：
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>              5
 * <p>             / \
 * <p>            4   8
 * <p>           /   / \
 * <p>          11  13  4
 * <p>         /  \      \
 * <p>        7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 * @author Ls J
 * @date 2020/7/7 12:12 AM
 */
public class PathSum112 {

    /**
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.8 MB，在所有 Java 提交中击败了 6.52% 的用户
     *
     * @param root root
     * @param sum  sum
     * @return true / false
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
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
