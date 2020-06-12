package com.lidong.algorithm.leetcode.medium.tree;

/**
 * 二叉树的最近公共祖先（中等-236）
 * 中文链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * <p>
 * 问题描述：
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 示例 1:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * <p>
 * 示例 2:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 * <p>
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 *
 * @author Ls J
 * @date 2020/6/12 10:24 PM
 */
public class LowestCommonAncestorOfaBinaryTree236 {

    /**
     * 执行用时：7 ms，在所有 Java 提交中击败了 99.92% 的用户
     * 内存消耗：42.1 MB，在所有 Java 提交中击败了 5.71% 的用户
     *
     * @param root root
     * @param p    p
     * @param q    q
     * @return ancestor
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 等于 p 或者 q 的时候不能再往下走了
        if (null == root || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // left 为 null，说明都在右子树
        if (null == left) {
            return right;
        }
        // 同理
        if (null == right) {
            return left;
        }
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
