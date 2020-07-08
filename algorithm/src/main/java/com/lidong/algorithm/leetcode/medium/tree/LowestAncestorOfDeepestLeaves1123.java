package com.lidong.algorithm.leetcode.medium.tree;

/**
 * 最深叶节点的最近公共祖先（中等-1123）
 * 中文链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-deepest-leaves
 * <p>
 * 问题描述：
 * 给你一个有根节点的二叉树，找到它最深的叶节点的最近公共祖先。
 * 回想一下：
 * 1.叶节点是二叉树中没有子节点的节点
 * 2.树的根节点的 深度 为 0，如果某一节点的深度为 d，那它的子节点的深度就是 d+1
 * 3.如果我们假定 A 是一组节点 S 的 最近公共祖先，S 中的每个节点都在以 A 为根节点的子树中，且 A 的深度达到此条件下可能的最大值。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：[1,2,3]
 * 解释：
 * 最深的叶子是值为 2 和 3 的节点。
 * 这些叶子的最近共同祖先是值为 1 的节点。
 * 返回的答案为序列化的 TreeNode 对象（不是数组）"[1,2,3]" 。
 * <p>
 * 示例 2：
 * 输入：root = [1,2,3,4]
 * 输出：[4]
 * <p>
 * 示例 3：
 * 输入：root = [1,2,3,4,5]
 * 输出：[2,4,5]
 * <p>
 * 提示：
 * 1.给你的树中将有 1 到 1000 个节点。
 * 2.树中每个节点的值都在 1 到 1000 之间。
 *
 * @author ls J
 * @date 2020/7/8 12:30
 */
public class LowestAncestorOfDeepestLeaves1123 {

    /**
     * 执行用时：1 ms，在所有 Java 提交中击败了 70.38% 的用户
     * 内存消耗：39.6 MB，在所有 Java 提交中击败了 25.00% 的用户
     *
     * @param root root
     * @return lowest root
     */
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 计算左右深度
        int ld = depth(root.left);
        int rd = depth(root.right);
        if (ld == rd) {
            return root;
        } else if (ld > rd) {
            return lcaDeepestLeaves(root.left);
        } else {
            return lcaDeepestLeaves(root.right);
        }
    }

    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = depth(node.right);
        int right = depth(node.left);
        return Math.max(left, right) + 1;
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
