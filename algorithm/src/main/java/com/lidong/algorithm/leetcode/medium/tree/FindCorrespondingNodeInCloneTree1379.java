package com.lidong.algorithm.leetcode.medium.tree;

/**
 * 找出克隆二叉树中的相同节点（中等-1379）
 * 中文链接：https://leetcode-cn.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/
 * <p>
 * 问题描述：
 * 给你两棵二叉树，原始树 original 和克隆树 cloned，以及一个位于原始树 original 中的目标节点 target。
 * 其中，克隆树 cloned 是原始树 original 的一个 副本 。
 * 请找出在树 cloned 中，与 target 相同 的节点，并返回对该节点的引用（在 C/C++ 等有指针的语言中返回 节点指针，其他语言返回节点本身）。
 * <p>
 * 注意：
 * - 你不能对两棵二叉树，以及 target 节点进行更改。
 * - 只能 返回对克隆树 cloned 中已有的节点的引用。
 * - 进阶：如果树中允许出现值相同的节点，你将如何解答？
 *
 * @author Ls J
 * @date 2020/7/5 1:36 PM
 */
public class FindCorrespondingNodeInCloneTree1379 {

    /**
     * dfs：直接对内存地址进行比较
     * 执行用时：1 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：47.4 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param original original
     * @param cloned   cloned
     * @param target   target
     * @return TreeNOde
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        // 没找到，返回 null
        if (null == original) {
            return null;
        }
        // 找到了，直接返回
        if (original == target) {
            return cloned;
        }
        // 找左子树
        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        if (null != left) {
            return left;
        }
        // 找右子树
        return getTargetCopy(original.right, cloned.right, target);
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
