package com.lidong.algorithm.leetcode.medium.tree;

/**
 * 在二叉树中分配硬币（中等-979）
 * 中文链接：https://leetcode-cn.com/problems/distribute-coins-in-binary-tree
 * <p>
 * 问题描述：
 * 给定一个有 N 个结点的二叉树的根结点 root，树中的每个结点上都对应有 node.val 枚硬币，并且总共有 N 枚硬币。
 * 在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点。(移动可以是从父结点到子结点，或者从子结点移动到父结点。)。
 * 返回使每个结点上只有一枚硬币所需的移动次数。 
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,0,0]
 * 输出：2
 * 解释：从树的根结点开始，我们将一枚硬币移到它的左子结点上，一枚硬币移到它的右子结点上。
 * <p>
 * 示例 2：
 * 输入：[0,3,0]
 * 输出：3
 * 解释：从根结点的左子结点开始，我们将两枚硬币移到根结点上 [移动两次]。然后，我们把一枚硬币从根结点移到右子结点上。
 * <p>
 * 示例 3：
 * 输入：[1,0,2]
 * 输出：2
 * <p>
 * 示例 4：
 * 输入：[1,0,0,null,3]
 * 输出：4
 *  
 * 提示：
 * 1<= N <= 100
 * 0 <= node.val <= N
 *
 * @author ls J
 * @date 2020/7/3 17:53
 */
public class DistributeCoinsInBinaryTree979 {

    private int res = 0;

    /**
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：38.9 MB，在所有 Java 提交中击败了 33.33% 的用户
     *
     * @param root root
     * @return times
     */
    public int distributeCoins(TreeNode root) {
        dfs(root);
        return res;
    }

    /**
     * dfs
     * <p>
     * 如果树的叶子仅包含 0 枚金币（与它所需相比，它的过载量为 -1），那么我们需要从它的父亲节点移动一枚金币到这个叶子节点上。
     * 如果说，一个叶子节点包含 4 枚金币（它的过载量为 3），那么我们需要将这个叶子节点中的 3 枚金币移动到别的地方去。
     * 总的来说，对于一个叶子节点，需要移动到它中或需要从它移动到它的父亲中的金币数量为 过载量 = Math.abs(num_coins - 1)。
     * 然后，在接下来的计算中，我们就再也不需要考虑这些已经考虑过的叶子节点了。
     *
     * @param root root
     * @return times
     */
    private int dfs(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int l = dfs(root.left);
        int r = dfs(root.right);
        res += Math.abs(l) + Math.abs(r);
        // 过载量 = 节点已有的硬币个数 - 1
        return root.val + l + r - 1;
    }

    private class TreeNode {
        int val;

        TreeNode left;

        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
