package com.lidong.algorithm.leetcode.middling.dfs;

/**
 * 打家劫舍III（中等-337）
 * 问题描述：
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
 * 这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 *
 * 示例 2:
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 * @author ls J
 * @date 2020/4/23 21:18
 */
public class HouseRobberIII337 {

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] result = recurse(root);
        return Math.max(result[0], result[1]);
    }

    /**
     * 对于每个节点都有两种情况：
     * 1. 如果偷当前节点，那么它的两个儿子节点都不能偷
     * 2. 如果不偷当前节点，那么两个儿子节点都要给出最多的前
     * <p>
     * 因此每个节点可以用一个长度为 2 的数组来表示，其中第一个元素代表偷当前节点所能贡献的最大值，第二个元素代表不偷当前节点所能贡献的最大值
     *
     * @param node node
     * @return int[]
     */
    private int[] recurse(TreeNode node) {
        int[] result = new int[2];
        if (node == null) {
            return result;
        }
        // 计算当前节点左儿子偷与不偷所能获得的收益
        int[] left = recurse(node.left);
        // 计算当前节点右儿子偷与不偷所能获得的收益
        int[] right = recurse(node.right);
        // 不偷当前节点所能获得的最大收益 = 左儿子所能获得的最大收益 + 右儿子所能获得的最大收益
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // 偷当前节点所能获得的最大收益 = 偷当前节点的钱 + 不偷左儿子所获得的钱 +不偷右儿子所获得的钱
        result[1] = node.val + left[0] + right[0];
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(10);
        root.left = node1;
        node1.left = node2;
        node1.right = node3;
        HouseRobberIII337 robberIII = new HouseRobberIII337();
        System.out.println(robberIII.rob(root));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
