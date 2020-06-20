package com.lidong.algorithm.leetcode.medium.tree;

/**
 * 验证二叉搜索树（中等-98）
 * 中文链接：https://leetcode-cn.com/problems/validate-binary-search-tree/
 * <p>
 * 问题描述：
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 示例 1:
 * 输入:
 * <p>    2
 * <p>   / \
 * <p>  1   3
 * 输出: true
 * <p>
 * 示例 2:
 * 输入:
 * <p>    5
 * <p>   / \
 * <p>  1   4
 * <p>     / \
 * <p>    3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * @author Ls J
 * @date 2020/6/20 3:33 PM
 */
public class ValidateBinarySearchTree98 {

    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    /**
     * 递归遍历
     *
     * @param root  当前根节点
     * @param lower 当前子树值下界
     * @param upper 当前子树值上界
     * @return true 如果当前子树是一棵搜索树
     */
    private boolean helper(TreeNode root, Integer lower, Integer upper) {
        if (null == root) {
            return true;
        }
        int val = root.val;
        if (null != lower && val <= lower) {
            return false;
        }
        if (null != upper && val >= upper) {
            return false;
        }
        if (!helper(root.right, val, upper)) {
            return false;
        }
        return helper(root.left, lower, val);
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
