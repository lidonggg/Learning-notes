package com.lidong.algorithm.leetcode.medium.stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的前序遍历（中等-144）
 * 中文链接：
 * <p>
 * 问题描述：
 * 给定一个二叉树，返回它的前序遍历，利用迭代的方式
 *
 * @author Ls J
 * @date 2020/6/25 1:24 PM
 */
public class BinaryTreePreorderTraversal144 {

    /**
     * 方法一：利用栈 FILO 的特性
     * <p>
     * 执行用时：1 ms，在所有 Java 提交中击败了 48.06% 的用户
     * 内存消耗：38 MB，在所有 Java 提交中击败了 6.38% 的用户
     *
     * @param root root
     * @return list
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (null == root) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode top = stack.pop();
            res.add(top.val);
            if (null != top.right) {
                stack.push(top.right);
            }
            if (null != top.left) {
                stack.push(top.left);
            }
        }
        return res;
    }

    /**
     * 方法二：莫里斯遍历
     * 来自 leetcode 官方题解：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/er-cha-shu-de-qian-xu-bian-li-by-leetcode/
     * <p>
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：38.1 MB，在所有 Java 提交中击败了 6.38% 的用户
     * <p>
     * 算法的思路是从当前节点向下访问先序遍历的前驱节点，每个前驱节点都恰好被访问两次。
     * 首先从当前节点开始，向左孩子走一步然后沿着右孩子一直向下访问，直到到达一个叶子节点（当前节点的中序遍历前驱节点），
     * 所以我们更新输出并建立一条伪边 predecessor.right = root 更新这个前驱的下一个点。
     * 如果我们第二次访问到前驱节点，由于已经指向了当前节点，我们移除伪边并移动到下一个顶点。
     * 如果第一步向左的移动不存在，就直接更新输出并向右移动。
     *
     * @param root root
     * @return list
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();

        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {
                res.add(node.val);
                node = node.right;
            } else {
                TreeNode predecessor = node.left;
                while ((predecessor.right != null) && (predecessor.right != node)) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    res.add(node.val);
                    predecessor.right = node;
                    node = node.left;
                } else {
                    predecessor.right = null;
                    node = node.right;
                }
            }
        }
        return res;
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
