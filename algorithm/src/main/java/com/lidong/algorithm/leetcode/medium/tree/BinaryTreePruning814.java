package com.lidong.algorithm.leetcode.medium.tree;

/**
 * 二叉树剪枝（中等-814）
 * 中文链接：https://leetcode-cn.com/problems/binary-tree-pruning
 * <p>
 * 问题描述：
 * 给定二叉树根结点 root ，此外树的每个结点的值要么是 0，要么是 1。
 * 返回移除了所有不包含 1 的子树的原二叉树。
 * ( 节点 X 的子树为 X 本身，以及所有 X 的后代。)
 * <p>
 * 示例1:
 * 输入: [1,null,0,0,1]
 * 输出: [1,null,0,null,1]
 * 解释:
 * 只有红色节点满足条件“所有不包含 1 的子树”。
 * 右图为返回的答案。
 * <p>
 * 示例2:
 * 输入: [1,0,1,0,0,0,1]
 * 输出: [1,null,1,null,1]
 * <p>
 * 示例3:
 * 输入: [1,1,0,1,1,0,1,0]
 * 输出: [1,1,0,1,1,null,1]
 * <p>
 * 说明:
 * 1.给定的二叉树最多有 100 个节点。
 * 2.每个节点的值只会为 0 或 1 。
 *
 * @author Ls J
 * @date 2020/6/30 12:19 AM
 */
public class BinaryTreePruning814 {

    /**
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：37.2 MB，在所有 Java 提交中击败了 8.33% 的用户
     *
     * @param root root
     * @return new root
     */
    public TreeNode pruneTree(TreeNode root) {
        // 如果当前子树根结点为 null 或者说有 1，那么直接返回 null
        if (null == root || dfs(root)) {
            return null;
        }
        // 重写左右子树
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        // 返回根节点
        return root;
    }

    /**
     * dfs 判断当前子树有没有 1
     *
     * @param root root
     * @return true if none 1
     */
    private boolean dfs(TreeNode root) {
        // 代表子树没有 1
        if (null == root) {
            return true;
        }
        // 一旦出现了 1 就返回 false
        if (1 == root.val) {
            return false;
        }

        return dfs(root.left) && dfs(root.right);
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
