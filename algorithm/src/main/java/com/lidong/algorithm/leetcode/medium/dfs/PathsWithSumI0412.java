package com.lidong.algorithm.leetcode.medium.dfs;

/**
 * 求和路径（中等-面试题04_12）
 * 中文链接：https://leetcode-cn.com/problems/paths-with-sum-lcci
 * <p>
 * 问题描述：
 * 给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>              5
 * <p>             / \
 * <p>            4   8
 * <p>           /   / \
 * <p>          11  13  4
 * <p>         /  \    / \
 * <p>        7    2  5   1
 * 返回:
 * 3
 * 解释：和为 22 的路径有：[5,4,11,2], [5,8,4,5], [4,11,7]
 * <p>
 * 提示：
 * 节点总数 <= 10000
 *
 * @author ls J
 * @date 2020/7/14 16:17
 */
public class PathsWithSumI0412 {

    /**
     * 计算以每个节点为起始节点的所有和为 sum 的路径数
     * <p>
     * 执行用时：9 ms，在所有 Java 提交中击败了 49.48% 的用户
     * 内存消耗：39.5 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param root root
     * @param sum  sum
     * @return num
     */
    public int pathSum(TreeNode root, int sum) {
        if (null == root) {
            return 0;
        }
        return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    /**
     * dfs 计算以某个节点为起始节点的所有和为 sum 的路径数
     *
     * @param root root
     * @param rem  rem
     * @return num
     */
    private int dfs(TreeNode root, int rem) {
        if (null == root) {
            return 0;
        }

        rem -= root.val;
        int res = rem == 0 ? 1 : 0;

        res += dfs(root.left, rem);
        res += dfs(root.right, rem);

        return res;
    }

    private static class TreeNode {

        int val;

        TreeNode left;

        TreeNode right;
    }
}
