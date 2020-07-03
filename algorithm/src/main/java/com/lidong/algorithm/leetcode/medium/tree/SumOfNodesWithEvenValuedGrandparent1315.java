package com.lidong.algorithm.leetcode.medium.tree;

/**
 * 祖父节点值为偶数的节点和（中等-1315）
 * 中文链接：https://leetcode-cn.com/problems/sum-of-nodes-with-even-valued-grandparent
 * <p>
 * 问题描述：
 * 给你一棵二叉树，请你返回满足以下条件的所有节点的值之和：
 * 该节点的祖父节点的值为偶数。（一个节点的祖父节点是指该节点的父节点的父节点。）
 * 如果不存在祖父节点值为偶数的节点，那么返回 0 。
 * <p>
 * 示例：
 * 输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * 输出：18
 * 解释：图中红色节点的祖父节点的值为偶数，蓝色节点为这些红色节点的祖父节点。
 * <p>
 * 提示：
 * 1.树中节点的数目在 1 到 10^4 之间。
 * 2.每个节点的值在 1 到 100 之间。
 *
 * @author ls J
 * @date 2020/7/3 18:28
 */
public class SumOfNodesWithEvenValuedGrandparent1315 {

    private int sum = 0;

    /**
     * dfs
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：41.4 MB，在所有 Java 提交中击败了 100.00% 的用户
     *
     * @param root root
     * @return sum
     */
    public int sumEvenGrandparent(TreeNode root) {
        if (null == root) {
            return 0;
        }
        dfs(root);

        return sum;
    }

    private void dfs(TreeNode root) {
        if (null == root) {
            return;
        }
        if (root.val % 2 != 0) {
            dfs(root.left);
            dfs(root.right);
            return;
        }

        if (null != root.left) {
            sum += null == root.left.left ? 0 : root.left.left.val;
            sum += null == root.left.right ? 0 : root.left.right.val;
            dfs(root.left);
        }
        if (null != root.right) {
            sum += null == root.right.left ? 0 : root.right.left.val;
            sum += null == root.right.right ? 0 : root.right.right.val;
            dfs(root.right);
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
