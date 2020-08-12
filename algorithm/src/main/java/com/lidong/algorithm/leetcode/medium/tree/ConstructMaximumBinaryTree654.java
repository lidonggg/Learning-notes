package com.lidong.algorithm.leetcode.medium.tree;

/**
 * 构造最大二叉树（中等-654）
 * 中文链接：https://leetcode-cn.com/problems/maximum-binary-tree
 * <p>
 * 问题描述：
 * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
 * 1. 二叉树的根是数组中的最大元素。
 * 2. 左子树是通过数组中最大值左边部分构造出的最大二叉树。
 * 3. 右子树是通过数组中最大值右边部分构造出的最大二叉树。
 * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
 * <p>
 * 示例 ：
 * 输入：[3,2,1,6,0,5]
 * 输出：返回下面这棵树的根节点：
 * <p>      6
 * <p>    /   \
 * <p>   3     5
 * <p>    \    /
 * <p>     2  0
 * <p>       \
 * <p>        1
 * <p>
 * 提示：
 * 给定的数组的大小在 [1, 1000] 之间。
 *
 * @author ls J
 * @date 2020/8/12 9:30 PM
 */
public class ConstructMaximumBinaryTree654 {

    /**
     * 执行用时：2 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：39.8 MB，在所有 Java 提交中击败了 72.44% 的用户
     *
     * @param nums nums
     * @return root node
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    /**
     * 递归构造
     *
     * @param nums nums
     * @param l    区间下界
     * @param r    区间上界
     * @return root node
     */
    private TreeNode dfs(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        // [l, r] 区间内值最大的索引
        int maxIdx = findMaxIdx(nums, l, r);
        TreeNode root = new TreeNode(nums[maxIdx]);
        root.left = dfs(nums, l, maxIdx - 1);
        root.right = dfs(nums, maxIdx + 1, r);

        return root;
    }

    /**
     * 寻找区间最大值的下标
     *
     * @param nums nums
     * @param l    l
     * @param r    r
     * @return idx
     */
    private int findMaxIdx(int[] nums, int l, int r) {
        int maxIdx = l, maxVal = nums[l];
        for (int i = l + 1; i <= r; ++i) {
            if (maxVal < nums[i]) {
                maxIdx = i;
                maxVal = nums[i];
            }
        }
        return maxIdx;
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
