package com.lidong.algorithm.leetcode.easy.tree;

/**
 * 修剪二叉搜索树（简单-669）
 * 中文链接：https://leetcode-cn.com/problems/trim-a-binary-search-tree
 * <p>
 * 问题描述：
 * 给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。
 * 你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。
 * <p>
 * 示例 1:
 * 输入:
 * <p>    1
 * <p>   / \
 * <p>  0   2
 * L = 1
 * R = 2
 * 输出:
 * <p>    1
 * <p>      \
 * <p>       2
 * <p>
 * 示例 2:
 * 输入:
 * <p>   3
 * <p>   / \
 * <p>  0   4
 * <p>   \
 * <p>    2
 * <p>   /
 * <p>  1
 * L = 1
 * R = 3
 * 输出:
 * <p>      3
 * <p>     /
 * <p>   2
 * <p>  /
 * <p> 1
 *
 * @author Ls J
 * @date 2020/8/3 10:17 PM
 */
public class TrimBinarySearchTree669 {

    /**
     * 递归，不要抠细节
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.5 MB，在所有 Java 提交中击败了 62.79% 的用户
     *
     * @param root root
     * @param l    L
     * @param r    R
     * @return root
     */
    public TreeNode trimBST(TreeNode root, int l, int r) {
        if (null == root) {
            return null;
        }

        if (root.val < l) {
            return trimBST(root.right, l, r);
        }

        if (root.val > r) {
            return trimBST(root.left, l, r);
        }
        root.left = trimBST(root.left, l, r);
        root.right = trimBST(root.right, l, r);
        return root;
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
