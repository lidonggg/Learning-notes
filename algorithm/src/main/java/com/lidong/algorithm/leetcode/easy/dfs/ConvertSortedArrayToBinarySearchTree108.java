package com.lidong.algorithm.leetcode.easy.dfs;

/**
 * 将有序数组转换为二叉搜索树（简单-108）
 * 中文链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 * <p>
 * 问题描述：
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * @author Ls J
 * @date 2020/6/21 11:31 AM
 */
public class ConvertSortedArrayToBinarySearchTree108 {

    /**
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.6 MB，在所有 Java 提交中击败了 8.70% 的用户
     *
     * @param nums nums
     * @return root
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    /**
     * dfs
     *
     * @param nums nums
     * @param l    左边界
     * @param r    右边界
     * @return 子树根结点
     */
    private TreeNode dfs(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        // 取中点
        int mid = l + ((r - l) >> 1);

        TreeNode node = new TreeNode(nums[mid]);

        node.left = dfs(nums, l, mid - 1);
        node.right = dfs(nums, mid + 1, r);
        return node;
    }

    private static class TreeNode {

        int val;

        TreeNode left;

        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
